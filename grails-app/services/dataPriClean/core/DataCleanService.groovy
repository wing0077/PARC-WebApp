package dataPriClean.core

import java.util.Map;

import grails.transaction.Transactional
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

@Transactional
class DataCleanService {
	def dataService
	
	//get recommendation result for individual searching type and config
	String getRecommendationsInd(def targetDataset, def masterDataset, def simThreshold, def searchObj, Map<String, Double> config) {
		DataCleaningUtils dataCleanUtil = new DataCleaningUtilsImpl()
		
		String result = ""
		
		TargetDataset target = dataService.loadTargetDataset(targetDataset.url, targetDataset.name, targetDataset.dbConstraint.url)
		MasterDataset master = dataService.loadMasterDataset(masterDataset.url, masterDataset.name, masterDataset.dbConstraint.url, targetDataset.id)
		
		if (target && master) {
		
			float simThresholdF = Float.parseFloat(simThreshold);
			
			SearchType searchType
			
			switch (searchObj) {
				case "weighted":
					//TODO
					searchType = SearchType.SA_WEIGHTED
//					searchType = SearchType.BRUTE_FORCE
					break
				case "constrained":
					searchType = SearchType.SA_EPS_FLEX
					break
				case "dynamic":
					searchType = SearchType.SA_EPS_DYNAMIC
					break
				case "lexical":
					searchType = SearchType.SA_EPS_LEX
					break
				default:
					searchType = SearchType.SA_EPS
					break
			}
			
			result = dataCleanUtil.runDataCleaning(target, master, simThresholdF, searchType, config)
		}
		else {
			println("Target & Master Dataset Not Found!")
		}
		
		return result
	}
	
	//get recommendation result for multiple searching type and config
	String getRecommendations(def targetDataset, def masterDataset, def simThreshold, def searchObj, def config) {
		StringBuilder sb = new StringBuilder()
		
		if (searchObj) {
			searchObj.each {
				switch (it) {
					case "weighted":
						def s
						if (config && config["weighted"]) {
							s = getRecommendationsInd(targetDataset, masterDataset, simThreshold, it, config["weighted"])
						}
						else {
							s = getRecommendationsInd(targetDataset, masterDataset, simThreshold, it, null)
						}
						sb.append("====================================== \n")
						sb.append("For weighted algorithm: \n")
						sb.append(s + "\n")
						break
					case "dynamic":
						def s
						def dy = simThreshold-0.5 
						if (config && config["dynamic"]) {
							s = getRecommendationsInd(targetDataset, masterDataset, dy, it, config["dynamic"])
						}
						else {
							s = getRecommendationsInd(targetDataset, masterDataset, dy, it, null)
						}
						sb.append("====================================== \n")
						sb.append("For dynamic algorithm: \n")
						sb.append(s + "\n")
						break
					case "lexical":
						def s
						if (config && config["lexical"]) {
							s = getRecommendationsInd(targetDataset, masterDataset, simThreshold, it, config["lexical"])
						}
						else {
							s = getRecommendationsInd(targetDataset, masterDataset, simThreshold, it, null)
						}
						sb.append("====================================== \n")
						sb.append("For lexical algorithm: \n")
						sb.append(s + "\n")
						break
					case "constrained":
						def s
						if (config && config["constrained"]) {
							s = getRecommendationsInd(targetDataset, masterDataset, simThreshold, it, config["constrained"])
						}
						else {
							s = getRecommendationsInd(targetDataset, masterDataset, simThreshold, it, null)
						}
						sb.append("====================================== \n")
						sb.append("For constrained algorithm: \n")
						sb.append(s + "\n")
						break
				}
			}
		}
		
		return sb.toString()
	}
	
	//get recommendation result for individual searching type and config
	def getRecommendationsIndList(def targetDataset, def masterDataset, def simThreshold, def searchObj, Map<String, Double> config) {
		DataCleaningUtils dataCleanUtil = new DataCleaningUtilsImpl()
		
		def result = [:]
		
		result["search"] = searchObj
		
		TargetDataset target = dataService.loadTargetDataset(targetDataset.url, targetDataset.name, targetDataset.dbConstraint.url)
		MasterDataset master = dataService.loadMasterDataset(masterDataset.url, masterDataset.name, masterDataset.dbConstraint.url, targetDataset.id)
		
		if (target && master) {
		
			float simThresholdF = Float.parseFloat(simThreshold);
			
			SearchType searchType
			
			switch (searchObj) {
				case "weighted":
					//TODO
					searchType = SearchType.SA_WEIGHTED
//					searchType = SearchType.BRUTE_FORCE
					break
				case "constrained":
					searchType = SearchType.SA_WEIGHTED
					break
				case "dynamic":
					searchType = SearchType.SA_WEIGHTED
					break
				case "lexical":
					searchType = SearchType.SA_WEIGHTED
					break
				default:
					searchType = SearchType.SA_EPS
					break
			}
			
			//Very Important!!!!!!!!!!!!!!!
			//get different format of result in Candidate map or Recommendation Patterns
			
			// this return Map<Constraint, Set<Candidate>>
			def recommendation = dataCleanUtil.runDataCleaningMap(target, master, simThresholdF, searchType, config)
			
//			// this return Map<Constraint, List<List<RecommendationPattern>>>
//			def recommendation = dataCleanUtil.runDataCleaningMapWithPatterns(target, master, simThresholdF, searchType, config)
			result["recommendation"] = recommendation
		}
		else {
			println("Target & Master Dataset Not Found!")
		}
		
		return result
	}
	
	//get recommendation result for multiple searching type and config
	def getRecommendationsList(def targetDataset, def masterDataset, def simThreshold, def searchObj, def config) {
		def result = []
		
		if (searchObj) {
			searchObj.each {
				switch (it) {
					case "weighted":
						def s
						def we = (String)(float)(Double.parseDouble(simThreshold)*0.35)
						if (config && config["weighted"]) {
							s = getRecommendationsIndList(targetDataset, masterDataset, we, it, config["weighted"])
						}
						else {
							s = getRecommendationsIndList(targetDataset, masterDataset, we, it, null)
						}
						result.add(s)
						break
					case "dynamic":
						def s
//						def dy = (String)(float)(Double.parseDouble(simThreshold)/3)
						def dy = (String)(float)(Math.abs(Double.parseDouble(simThreshold)*0.35-0.05))
//						System.out.println(dy)
						if (config && config["dynamic"]) {
							s = getRecommendationsIndList(targetDataset, masterDataset, dy, it, config["dynamic"])
						}
						else {
							s = getRecommendationsIndList(targetDataset, masterDataset, dy, it, null)
						}
						result.add(s)
						break
					case "lexical":
						def s
//						def le = (String)(float)(Double.parseDouble(simThreshold)/4)
						def le = (String)(float)(Math.abs(Double.parseDouble(simThreshold)*0.35-0.1))
						if (config && config["lexical"]) {
							s = getRecommendationsIndList(targetDataset, masterDataset, le, it, config["lexical"])
						}
						else {
							s = getRecommendationsIndList(targetDataset, masterDataset, le, it, null)
						}
						result.add(s) 
						
						break
					case "constrained":
						def s
//						def co = (String)(float)(Double.parseDouble(simThreshold)/2)
						def co = (String)(float)(Math.abs(Double.parseDouble(simThreshold)*0.35-0.15))
						if (config && config["constrained"]) {
							s = getRecommendationsIndList(targetDataset, masterDataset, co, it, config["constrained"])
						}
						else {
							s = getRecommendationsIndList(targetDataset, masterDataset, co, it, null)
						}
						result.add(s)
						break
				}
			}
		}
		
		return result
	}
}
