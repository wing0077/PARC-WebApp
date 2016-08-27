package dataPriClean.core

import java.util.List;

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(DataCleanService)
class DataCleanServiceSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

	//small dataset load test with 6 records
    void "test loadTargetDataset"() {
		def before = System.currentTimeMillis()
		
		given:
		String url = "/Users/thomas/Documents/Programming/DataPrivacy/resource/data/testdata1/address.csv"
		String fileName = "address.csv"
		String fdUrl = "/Users/thomas/Documents/Programming/DataPrivacy/resource/data/testdata1/address_fd2.csv"
		char separator = ','
		char quoteChar = '\''
		
		when:
		def dataset = service.loadTargetDataset(url, fileName, fdUrl, separator, quoteChar)
		
		def after = System.currentTimeMillis()
		
		then:
		dataset != null
		
		println("Number of records: " + dataset.getRecords().size())
		println("Number of constraints: " + dataset.getConstraints().size())
		println("Running time: " + (after - before) + "ms")
    }
	
	//dataset load test with 3,000 records
	void "test loadTargetDataset1"() {
		def before = System.currentTimeMillis()
		
		given:
		String url = "/Users/thomas/Documents/Programming/DataPrivacy/resource/data/testdata4/Japan_trials.csv"
		String fileName = "Japan_trials.csv"
		String fdUrl = "/Users/thomas/Documents/Programming/DataPrivacy/resource/data/testdata4/fds.csv";
		char separator = ','
		char quoteChar = '"'
		
		when:
		def dataset = service.loadTargetDataset(url, fileName, fdUrl, separator, quoteChar)
		
		def after = System.currentTimeMillis()
		
		then:
		dataset != null
		
		println("Number of records: " + dataset.getRecords().size())
		println("Number of constraints: " + dataset.getConstraints().size())
		println("Running time: " + (after - before) + "ms")
	}
	
	//dataset load test with 10,000 records
	void "test loadTargetDataset2"() {
		def before = System.currentTimeMillis()
		
		given:
		String url = "/Users/thomas/Documents/Programming/DataPrivacy/resource/data/testdata4/Canada_trials.csv"
		String fileName = "Canada_trials.csv"
		String fdUrl = "/Users/thomas/Documents/Programming/DataPrivacy/resource/data/testdata4/fds.csv";
		char separator = ','
		char quoteChar = '"'
		
		when:
		def dataset = service.loadTargetDataset(url, fileName, fdUrl, separator, quoteChar)
		
		def after = System.currentTimeMillis()
		
		then:
		dataset != null
		
		println("Number of records: " + dataset.getRecords().size())
		println("Number of constraints: " + dataset.getConstraints().size())
		println("Running time: " + (after - before) + "ms")
	}
	
	//dataset load test with 20k records
	void "test loadTargetDataset3"() {
		def before = System.currentTimeMillis()
		
		given:
		String url = "/Users/thomas/Documents/Programming/DataPrivacy/resource/data/testdata3/imdb_20k.csv"
		String fileName = "imdb_20k.csv"
		String fdUrl = "/Users/thomas/Documents/Programming/DataPrivacy/resource/data/testdata3/imdb_fds.csv";
		char separator = ','
		char quoteChar =  '"'
		
		when:
		def dataset = service.loadTargetDataset(url, fileName, fdUrl, separator, quoteChar)
		
		def after = System.currentTimeMillis()
		
		then:
		dataset != null
		
		println("Number of records: " + dataset.getRecords().size())
		println("Number of constraints: " + dataset.getConstraints().size())
		println("Running time: " + (after - before) + "ms")
	}
	
	//dataset load test with 200k records
	void "test loadTargetDataset4"() {
		def before = System.currentTimeMillis()
		
		given:
		String url = "/Users/thomas/Documents/Programming/DataPrivacy/resource/data/testdata3/imdb_200k.csv"
		String fileName = "imdb_400k.csv"
		String fdUrl = "/Users/thomas/Documents/Programming/DataPrivacy/resource/data/testdata3/imdb_fds.csv";
		char separator = ','
		char quoteChar =  '"'
		
		when:
		def dataset = service.loadTargetDataset(url, fileName, fdUrl, separator, quoteChar)
		
		def after = System.currentTimeMillis()
		
		then:
		dataset != null
		
		println("Number of records: " + dataset.getRecords().size())
		println("Number of constraints: " + dataset.getConstraints().size())
		println("Running time: " + (after - before) + "ms")
	}
	
	//test small dataset and constraint
	void "test findViolations"() {
		def before = System.currentTimeMillis()
		
		given:
		String url = "/Users/thomas/Documents/Programming/DataPrivacy/resource/data/testdata1/address.csv"
		String fileName = "address.csv"
		String fdUrl = "/Users/thomas/Documents/Programming/DataPrivacy/resource/data/testdata1/address_fd2.csv"
		char separator = ','
		char quoteChar = '\''
		
		when:
		def dataset = service.loadTargetDataset(url, fileName, fdUrl, separator, quoteChar)
		def vio = service.findViolations(dataset)
		
		def after = System.currentTimeMillis()
		
		then:
		dataset != null
		vio != null
		
		println ("Number of violations: " + calcVioNum(vio))
		//		println ("violations:" + vio)
		println("Number of records: " + dataset.getRecords().size())
		println("Number of constraints: " + dataset.getConstraints().size())
		println("Running time: " + (after - before) + "ms")
	}
	
	//test small dataset and constraint
	void "test findViolations with single fd"() {
		def before = System.currentTimeMillis()
		
		given:
		String url = "/Users/thomas/Documents/Programming/DataPrivacy/resource/data/testdata1/address.csv"
		String fileName = "address.csv"
		String fdUrl = "/Users/thomas/Documents/Programming/DataPrivacy/resource/data/testdata1/address_fd.csv"
		char separator = ','
		char quoteChar = '\''
		
		when:
		def dataset = service.loadTargetDataset(url, fileName, fdUrl, separator, quoteChar)
		def vio = service.findViolations(dataset)
		
		def after = System.currentTimeMillis()
		
		then:
		dataset != null
		vio != null
		
		println ("number of violations: " + calcVioNum(vio))
		//		println ("violations:" + vio)
		println("Number of records: " + dataset.getRecords().size())
		println("Number of constraints: " + dataset.getConstraints().size())
		println("Running time: " + (after - before) + "ms")
	}
	
	//test large dataset and constraint
	void "test findViolations1"() {
		def before = System.currentTimeMillis()
		
		given:
		String url = "/Users/thomas/Documents/Programming/DataPrivacy/resource/data/testdata4/Japan_trials.csv"
		String fileName = "Japan_trials.csv"
		String fdUrl = "/Users/thomas/Documents/Programming/DataPrivacy/resource/data/testdata4/fds.csv";
		char separator = ','
		char quoteChar = '"'
		
		when:
		def dataset = service.loadTargetDataset(url, fileName, fdUrl, separator, quoteChar)
		def vio = service.findViolations(dataset)
		
		def after = System.currentTimeMillis()
		
		then:
		dataset != null
		vio != null
		
		println ("number of violations: " + calcVioNum(vio))
		//		println ("violations1:" + vio)
		println("Number of records: " + dataset.getRecords().size())
		println("Number of constraints: " + dataset.getConstraints().size())
		println("Running time: " + (after - before) + "ms")
	}
	
	//test large dataset and constraint
	void "test findViolations1 with single fd"() {
		def before = System.currentTimeMillis()
		
		given:
		String url = "/Users/thomas/Documents/Programming/DataPrivacy/resource/data/testdata4/Japan_trials.csv"
		String fileName = "Japan_trials.csv"
		String fdUrl = "/Users/thomas/Documents/Programming/DataPrivacy/resource/data/testdata4/fds1.csv";
		char separator = ','
		char quoteChar = '"'
		
		when:
		def dataset = service.loadTargetDataset(url, fileName, fdUrl, separator, quoteChar)
		def vio = service.findViolations(dataset)
		
		def after = System.currentTimeMillis()
		
		then:
		dataset != null
		vio != null
		
		println ("number of violations: " + calcVioNum(vio))
		//		println ("violations1:" + vio)
		println("Number of records: " + dataset.getRecords().size())
		println("Number of constraints: " + dataset.getConstraints().size())
		println("Running time: " + (after - before) + "ms")
	}
	
	//test large dataset and constraint
	void "test findViolations2"() {
		def before = System.currentTimeMillis()
		
		given:
		String url = "/Users/thomas/Documents/Programming/DataPrivacy/resource/data/testdata4/Canada_trials.csv"
		String fileName = "Canada_trials.csv"
		String fdUrl = "/Users/thomas/Documents/Programming/DataPrivacy/resource/data/testdata4/fds.csv";
		char separator = ','
		char quoteChar = '"'
		
		when:
		def dataset = service.loadTargetDataset(url, fileName, fdUrl, separator, quoteChar)
		def vio = service.findViolations(dataset)
		
		def after = System.currentTimeMillis()
		
		then:
		dataset != null
		vio != null
		
		println ("number of violations: " + calcVioNum(vio))
		//		println ("violations2:" + vio)
		println("Number of records: " + dataset.getRecords().size())
		println("Number of constraints: " + dataset.getConstraints().size())
		println("Running time: " + (after - before) + "ms")
	}
	
	//test large dataset and constraint
	void "test findViolations2 with single fd"() {
		def before = System.currentTimeMillis()
		
		given:
		String url = "/Users/thomas/Documents/Programming/DataPrivacy/resource/data/testdata4/Canada_trials.csv"
		String fileName = "Canada_trials.csv"
		String fdUrl = "/Users/thomas/Documents/Programming/DataPrivacy/resource/data/testdata4/fds1.csv";
		char separator = ','
		char quoteChar = '"'
		
		when:
		def dataset = service.loadTargetDataset(url, fileName, fdUrl, separator, quoteChar)
		def vio = service.findViolations(dataset)
		
		def after = System.currentTimeMillis()
		
		then:
		dataset != null
		vio != null
		
		println ("number of violations: " + calcVioNum(vio))
		//		println ("violations2:" + vio)
		println("Number of records: " + dataset.getRecords().size())
		println("Number of constraints: " + dataset.getConstraints().size())
		println("Running time: " + (after - before) + "ms")
	}
	
	//test large dataset and constraint
	void "test findViolations3"() {
		def before = System.currentTimeMillis()
		
		given:
		String url = "/Users/thomas/Documents/Programming/DataPrivacy/resource/data/testdata3/imdb_20k.csv"
		String fileName = "imdb_20k.csv"
		String fdUrl = "/Users/thomas/Documents/Programming/DataPrivacy/resource/data/testdata3/imdb_fds.csv";
		char separator = ','
		char quoteChar = '"'
		
		when:
		def dataset = service.loadTargetDataset(url, fileName, fdUrl, separator, quoteChar)
		def vio = service.findViolations(dataset)
		
		def after = System.currentTimeMillis()
		
		then:
		dataset != null
		vio != null
		
		println ("number of violations: " + calcVioNum(vio))
		//		println ("violations2:" + vio)
		println("Number of records: " + dataset.getRecords().size())
		println("Number of constraints: " + dataset.getConstraints().size())
		println("Running time: " + (after - before) + "ms")
	}
	
	//test large dataset and constraint
	void "test findViolations3 with single fd"() {
		def before = System.currentTimeMillis()
		
		given:
		String url = "/Users/thomas/Documents/Programming/DataPrivacy/resource/data/testdata3/imdb_20k.csv"
		String fileName = "imdb_20k.csv"
		String fdUrl = "/Users/thomas/Documents/Programming/DataPrivacy/resource/data/testdata3/imdb_fd.csv";
		char separator = ','
		char quoteChar = '"'
		
		when:
		def dataset = service.loadTargetDataset(url, fileName, fdUrl, separator, quoteChar)
		def vio = service.findViolations(dataset)
		
		def after = System.currentTimeMillis()
		
		then:
		dataset != null
		vio != null
		
		println ("number of violations: " + calcVioNum(vio))
		//		println ("violations2:" + vio)
		println("Number of records: " + dataset.getRecords().size())
		println("Number of constraints: " + dataset.getConstraints().size())
		println("Running time: " + (after - before) + "ms")
	}
	
	//test large dataset and constraint
	void "test findViolations4"() {
		def before = System.currentTimeMillis()
		
		given:
		String url = "/Users/thomas/Documents/Programming/DataPrivacy/resource/data/testdata3/imdb_200k.csv"
		String fileName = "imdb_400k.csv"
		String fdUrl = "/Users/thomas/Documents/Programming/DataPrivacy/resource/data/testdata3/imdb_fds.csv";
		char separator = ','
		char quoteChar = '"'
		
		when:
		def dataset = service.loadTargetDataset(url, fileName, fdUrl, separator, quoteChar)
		def vio = service.findViolations(dataset)
		
		def after = System.currentTimeMillis()
		
		then:
		dataset != null
		vio != null
		
		println ("number of violations: " + calcVioNum(vio))
		//		println ("violations2:" + vio)
		println("Number of records: " + dataset.getRecords().size())
		println("Number of constraints: " + dataset.getConstraints().size())
		println("Running time: " + (after - before) + "ms")
	}
	
	//test large dataset and constraint
	void "test findViolations4 with single fd"() {
		def before = System.currentTimeMillis()
		
		given:
		String url = "/Users/thomas/Documents/Programming/DataPrivacy/resource/data/testdata3/imdb_200k.csv"
		String fileName = "imdb_400k.csv"
		String fdUrl = "/Users/thomas/Documents/Programming/DataPrivacy/resource/data/testdata3/imdb_fd.csv";
		char separator = ','
		char quoteChar = '"'
		
		when:
		def dataset = service.loadTargetDataset(url, fileName, fdUrl, separator, quoteChar)
		def vio = service.findViolations(dataset)
		
		def after = System.currentTimeMillis()
		
		then:
		dataset != null
		vio != null
		
		println ("number of violations: " + calcVioNum(vio))
		//		println ("violations2:" + vio)
		println("Number of records: " + dataset.getRecords().size())
		println("Number of constraints: " + dataset.getConstraints().size())
		println("Running time: " + (after - before) + "ms")
	}
	
	//calculate the total number of violations
	int calcVioNum (vios) {
		int count = 0
		for (vio in vios) {
			count += vio.violMap.size()
		}
		return count
	}
}


