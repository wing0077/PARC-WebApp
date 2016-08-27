package dataPriClean.user

import dataPriClean.data.Dataset

class Agent {
	String username
	String password
	String name
	
	Date dateCreated
	Date dateUpdated
	
	String role = "Target"
	
//	static hasMany = [datasets: Dataset]
	
    static constraints = {
		username(unique: true)
		password(password: true)
		
//		datasets(nullable: true)
		role(inList: ["Target", "Master"])
    }
	
	static mapping = {
//		datasets(cascade: 'delete, all-delete-orphan')
	}
	
	String toString () {
		name
	}
}
