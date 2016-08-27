package dataPriClean.core

import data.cleaning.core.service.dataset.impl.MasterDataset
import data.cleaning.core.service.dataset.impl.TargetDataset
import data.cleaning.core.service.dataset.impl.Constraint
import data.cleaning.core.service.repair.impl.Violation
import data.cleaning.core.service.repair.impl.Violations
import data.cleaning.core.service.dataset.DatasetService
import data.cleaning.core.service.repair.RepairService
import data.cleaning.core.service.dataset.impl.DatasetServiceImpl
import data.cleaning.core.service.repair.impl.RepairServiceImpl
import data.cleaning.core.utils.search.SearchType
import data.cleaning.webapp.core.DataCleaningUtils
import data.cleaning.webapp.core.implementation.DataCleaningUtilsImpl
import dataPriClean.data.DbConstraint
import dataPriClean.data.Dataset
import dataPriClean.user.Agent
import grails.transaction.Transactional

@Transactional
class DataService {

    TargetDataset loadTargetDataset (String url, 
									String fileName,
									String fdUrl, 
									char separator = ',', 
									char quoteChar = '\"') {
		DatasetService datasetService = new DatasetServiceImpl ()
		return datasetService.loadTargetDataset(url, fileName, fdUrl, separator, quoteChar)
	}
									
	MasterDataset loadMasterDataset (String url,
									String fileName,
									String fdUrl,
									Long tid,
									char separator = ',',
									char quoteChar = '\"') {
        DatasetService datasetService = new DatasetServiceImpl ()
		return datasetService.loadMasterDataset(url, fileName, fdUrl, tid, separator, quoteChar)
	}
									
	//delete individual file
	void deleteFileByUrl (String url) {
		File file = new File(url)
		
		if (!file.exists()) {
			println ("File does not exist, cannot delete the file!")
		}
		else {
			file.delete()
			println ("The file " + file.toString() + " was deleted succesfully!")
		}
	}
	
	//delete the dataset file, its constraint file and parent directory
	void deleteDatasetFile (String datasetUrl, String conUrl) {
		File datasetFile = new File(datasetUrl)
		File parentDir
		if (!datasetFile.exists()) {
			println ("File does not exist, cannot delete the file!")
			return
		}
		else {
		 parentDir = datasetFile.getParentFile()
		}
		
		deleteFileByUrl(datasetUrl)
		deleteFileByUrl(conUrl)
		parentDir.delete()
		
		println ("Delete dataset: " + datasetFile.toString() +
			" and its constraint file successfully!")
	}
}
