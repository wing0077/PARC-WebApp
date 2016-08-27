package dataPriClean.data

import dataPriClean.user.Agent

class TargetDataset extends Dataset{
	String masterAgent
	String masterDataset
	
	static belongsTo = [targetAgent: Agent]

    static constraints = {
		masterAgent(nullable: false)
		masterDataset(nullable: false)
    }
}
