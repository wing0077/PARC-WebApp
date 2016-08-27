package dataPriClean.data

import dataPriClean.user.Agent

class MasterDataset extends Dataset{
	
	static belongsTo = [masterAgent: Agent]

    static constraints = {
    }
}
