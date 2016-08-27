package dataPriClean.data

class Dataset {
	String name
	String url
	DbConstraint dbConstraint

    static constraints = {
		dbConstraint(nullable: true)
		name(unique: true)
    }
	
	static mapping = {
		dbConstraint(cascade: 'all-delete-orphan')
	}
	
	String toString () {
		name
	}
}
