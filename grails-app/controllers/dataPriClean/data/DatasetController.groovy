package dataPriClean.data

import dataPriClean.user.Agent
import dataPriClean.data.TargetDataset
import dataPriClean.data.MasterDataset

class DatasetController {
	def dataService
	
	//redirect to mastetDataset or targetDataset homepage according to login user
	def index () {
		def user = session.user
		
		//user login
		if (user) {
			//current user is target, show target datasets
			if (user.role.equals("Target")) {
				redirect (controller: "dataset", action: "targetDataset")
			}
			//current user is master, show master datasets
			else {
				redirect (controller: "dataset", action: "masterDataset")
			}
		}
		//user does not login
		else {
			println ("Please login first.")
			render(view: "/loginWarning.gsp")
		}
	}
	
	//show target datasets
	def targetDataset () {
		def user = session.user
		
		//user login
		if (user) {
			def datasets = TargetDataset.findAllByTargetAgent(user)
			def datasetsList = []
			
			datasets.each {
				def dataset = [:]
				dataset["datasetName"] = it.name
				dataset["conName"] = it.dbConstraint.name
				dataset["masterAgent"] = it.masterAgent
				dataset["masterDataset"] = it.masterDataset
				datasetsList.add(dataset)
			}
			
			[datasets: datasetsList]
		}
		//user does not login
		else {
			println ("Please login first.")
			render(view: "/loginWarning.gsp")
		}
	}
	
	//show master datsets
	def masterDataset () {
		def user = session.user
		
		//user login
		if (user) {
			def datasets = MasterDataset.findAllByMasterAgent(user)
			def datasetsList = []
			
			datasets.each {
				def dataset = [:]
				dataset["datasetName"] = it.name
				dataset["conName"] = it.dbConstraint.name
				datasetsList.add(dataset)
			}
			
			[datasets: datasetsList]
		}
		//user does not login
		else {
			println ("Please login first.")
			render(view: "/loginWarning.gsp")
		}
	}

    //upload master data and constraint page
	def uploadMasterDataset () {}
	
	//upload target data and constraint page
	def uploadTargetDataset () {}
	
	//save uploaded data and constraint info to the database
	def uploadData() {
		def user = session.user
		def dataFile = params.dataFile
		def conFile = params.conFile
		
		if (!user) {
			println ("Please login first.")
			render(view: "/loginWarning.gsp")
			return
		}
		
	    if (dataFile.isEmpty()) {
	        flash.message = "Dataset file cannot be empty!"
	        redirect(controller:"dataset", action: "index")
	        return
	    }
		
		if (conFile.isEmpty()) {
			flash.message = "Constraint file cannot be empty!"
			redirect(controller:"dataset", action: "index")
			return
		}
		
		//get current time
		Date date = new Date()
		def dateString = date.format("yyyy-MM-dd_hh:mm:ss")
		
		//save dataset & constraint files
		def webrootDir = servletContext.getRealPath("/") //app directory
		def fileDir = webrootDir + "dataset/" + user.username + "/" + dateString + "/"
		
		def dataFileName = fileDir + dataFile.getOriginalFilename()
		def conFileName = fileDir + conFile.getOriginalFilename()
		
		//TODO: this part of function should be put into service (save file)
		//save dataset file to the server
		File file = new File(dataFileName)
		if (!file.exists()) {
			if (!file.mkdirs()) {
				println "create file dir failed!"
			}
			if (!file.createNewFile()) {
				println "create file failed! file alrady exists!"
			}
		}
		dataFile.transferTo(file)
		
		//save constraint file to the server
		file = new File(conFileName)
		if (!file.exists()) {
			if (!file.mkdirs()) {
				println "create file dir failed!"
			}
			if (!file.createNewFile()) {
				println "create file failed! file alrady exists!"
			}
		}
		conFile.transferTo(file)
		
		String originalDataFileName = dataFile.getOriginalFilename()
		String originalConFielName = conFile.getOriginalFilename()
		
		//upload Master Dataset and create record in the db
		if (user.role.equals("Master")) {
			DbConstraint constraintT = new DbConstraint(
				name: originalConFielName,
				url: conFileName).save(flush: true)
				
			MasterDataset datasetT = new MasterDataset(
				name: originalDataFileName,
				url: dataFileName,
				dbConstraint: constraintT,
				masterAgent: user).save(flush: true)
		}
		//upload Target Datset and create record in the db
		else {
			def masterAgent = params.masterAgent
			def masterDataset = params.masterDataset
			
			if (!masterAgent) {
				flash.message = "Master Agent cannot be empty!"
				redirect(controller:"dataset", action: "uploadTargetDataset")
				return
			}
			
			if (!masterDataset) {
				flash.message = "Master Dataset cannot be empty!"
				redirect(controller:"dataset", action: "uploadTargetDataset")
				return
			}
			
			DbConstraint constraintT = new DbConstraint(
				name: originalConFielName,
				url: conFileName).save(flush: true)
			
			TargetDataset datasetT = new TargetDataset(
				name: originalDataFileName,
				url: dataFileName,
				dbConstraint: constraintT,
				masterAgent: masterAgent,
				masterDataset: masterDataset,
				targetAgent: user).save(flush: true)
		}
		
		redirect(controller:"dataset", action:"index")
	}
	
	//manage dataset for data quality & deletion
	def datasetManagement () {
		//inspect data quality
		if (params.func == "Violation Detection") {
			//select the target dataset for data quality
			session.targetDataset = params.dataset
			redirect(controller:"dataQuality",action:"findViolations")
		}
		//data cleaning
		else if (params.func == "Clean Data") {
			//select the target dataset for data quality
			session.targetDataset = params.dataset
			redirect(controller:"dataCleaning", action:"dataCleaningConfig")
		}
		//delete data
		else if (params.func == "Delete Data") {
			deleteDataset(params)
		}
		//show dataset details
		else if (params.func == "Show Dataset") {
			//select the dataset for showing
			session.showDatasetName = params.dataset
			redirect(controller:"dataset", action:"showDataset")
		}
	}
	
	def deleteDataset (def params) {
		def fileName = params.dataset
		
		def dataset = Dataset.findByName(fileName)
		
		if (dataset) {
			//TODO: handle error when deleting fails
			def con = dataset.dbConstraint
			
			String datasetUrl = dataset.url
			String conUrl = con.url
			
			//delete the dataset records in the database
			dataset.delete(flush: true)
			
			//delete the dataset & constraint & parent dir files
			dataService.deleteDatasetFile(datasetUrl, conUrl)
			
			println("Delete dataset: " + dataset.name + " successfully!")
		}
		else {
			flash.message = "Deleteing Dataset Not Found!"
			println ("Deleteing Dataset Not Found!")
		}
		
		redirect(controller:"dataset", action:"index")
	}
	
	def showDataset () {
		//get the dataset for showing
		def datasetName = session.showDatasetName
		
		def showDataset = [:]
		
		if (datasetName) {
			def dataset = Dataset.findByName(datasetName)
			if (dataset) {
				showDataset["datasetName"] = dataset.name
				showDataset["conName"] = dataset.dbConstraint.name
				
				def tDataset = dataService.loadTargetDataset(dataset.url, dataset.name, dataset.dbConstraint.url)
				def recordsList = tDataset.getRecords()
				
				//TODO: this part of function should be put into the service (get datset info)
				//the dataset is not empty
				if (recordsList.size() > 0) {
					def counter = 1
					//attributes of the dataset
					def attrs = recordsList.get(0).getColsToVal().keySet()
					//retrieve the dataset content by row and attribute
					def data = []
					for (def record: recordsList) {
						def recordRow = []
						recordRow.add(counter)
						for (def attr: attrs) {
							recordRow.add(record.getColsToVal().get(attr))
						}
						counter ++
						data.add(recordRow)
					}
					
					showDataset["attrs"] = attrs
					showDataset["data"] = data
				}
			}
			else {
				println ("Cannot find target dataset")
			}
		}
		else {
			println ("Target Dataset is not indicated")
		}
		
		[dataset: showDataset]
	}
}
