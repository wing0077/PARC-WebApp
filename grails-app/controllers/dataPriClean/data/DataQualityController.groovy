package dataPriClean.data

class DataQualityController {
	def dataQualityService
	def dataService
	
	//select dataset for data quality
	def datasetSelectionQua () {
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
	
	//get the selected data and execute vio detection
	def selectData () {
		def datasetName = params.dataset
		def dataset = TargetDataset.findByName(datasetName)
		
		if (dataset) {
			//select the target dataset for data quality
			session.targetDataset = dataset.name
			redirect(controller:"dataQuality", action:"findViolations")
		}
		//cannot find target dataset
		else {
			println ("Cannot find target dataset. ")
			flash.message = "Cannot find target dataset. "
			redirect(controller:"dataQuality", action:"datasetSelectionQua")
		}
	}

	//find the violations w.r.t the constraints
	def findViolations () {
		def vio = []
		
		//get the dataset for data quality
		def fileName = session.targetDataset
		def dataset = TargetDataset.findByName(fileName)
		
		if (dataset) {
			def con = dataset.dbConstraint
		
			if (con) {
				def datasetUrl = dataset.url
				def conUrl = con.url
				
				//load dataset & find violations
				def targetData = dataService.loadTargetDataset(datasetUrl, fileName, conUrl)
//				def violations = dataQualityService.findViolations(targetData)
				def violations = dataQualityService.findViolationsList(targetData)
				
				vio = violations
			}
			//this dataset does not have constraint
			else {
				println ("this dataset does not have constraint. ")
				flash.message = "this dataset does not have constraint. "
			}
		}
		else {
			flash.message = "Target Dataset Not Found!"
			println("Target Dataset Not Found!")
		}
		
		[vio:vio]
	}
}
