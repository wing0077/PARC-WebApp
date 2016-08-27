package dataPriClean.utils

import java.util.Map;

import grails.transaction.Transactional

@Transactional
class UtilsService {

    Map<String, Double> mapConvert (def map) {
		Map<String, Double> mapJava = new HashMap<String, Double>();
		
		map.each { key, value ->
			mapJava.put(key, value)
		}
		
		return mapJava
	}
	
	// convert [[Drug, Diphen , Benadryl, [2]], [Dosage, 25 mg , 20 mg, [2]], [Drug, Diphen , Banophen, [4]], [Dosage, 15 mg , 40 mg, [4]]] to a double string array
	def convertStringToDoubleArray (def s) {
		def result = []
		
		s = (String) s
		s = s[0..(s.length() - 2)]
		
		println "s1: " + s
		
		s = s.split(",")
		
		println "s2: " + s
		
		int counter = 0
		def temp = []
		for (def t: s) {
			def i = t
			println "i: " + i
			if (counter % 4 == 0) {
				temp = []
				result.add(temp)
			}
			if (counter % 4 == 0) {
				if (i.length() > 2) {
					i = i[2..(i.length() - 1)]
				}
			}
			if (counter % 4 == 3) {
				i = i[2..(i.length() - 3)]
				i = i.split(",")
			}
			temp.add(i)
			counter ++
		}
		
		return result
	}
}
