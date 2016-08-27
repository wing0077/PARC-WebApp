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
class DataQualityService {

   String findViolations (TargetDataset dataset) {
		RepairService repairService = new RepairServiceImpl()
		DataCleaningUtils dataCleanUtil = new DataCleaningUtilsImpl()
		
		List<Constraint> constraints = dataset.getConstraints()
		
		StringBuilder sb = new StringBuilder()

		for (Constraint constraint : constraints) {
			sb.append("========================================\n")
			sb.append("Constraint: ")
			sb.append(constraint.toString() + "\n")
			Violations v = repairService.calcViolations(dataset.records, constraint)
//			def s = dataCleanUtil.outputViolations(v, constraint)
//			sb.append(s + "\n")
			sb.append(v.toString() + "\n")
		}

		return sb.toString()
	}
   
   def findViolationsList (TargetDataset dataset) {
	   RepairService repairService = new RepairServiceImpl()
	   DataCleaningUtils dataCleanUtil = new DataCleaningUtilsImpl()
	   
	   List<Constraint> constraints = dataset.getConstraints()
	   
	   def result = []

	   for (Constraint constraint : constraints) {
		   def subResult = [:]
		   subResult["constraint"] = constraint.toString()
		   subResult["constraintAttrs"] = constraint.getColsInConstraint()
		   Violations v = repairService.calcViolations(dataset.records, constraint)
		   List<List<String>> s = dataCleanUtil.outputViolationsList(v, constraint)
		   subResult["violatons"] = s
		   result.add(subResult)
	   }
	   
//	   //TODO: delete later, only for test
//	   println "-------------------Violations-------------------------"
//	   for (def t: result) {
//		   def temp = t["violatons"]
//		   for (def subTemp: temp) {
//			   println subTemp[0]
//		   }
//	   }

	   return result
   }
}
