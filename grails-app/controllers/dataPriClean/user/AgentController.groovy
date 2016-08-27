package dataPriClean.user

import dataPriClean.data.DbConstraint
import dataPriClean.data.Dataset
import dataPriClean.user.Agent

class AgentController {
	def dataCleanService
	
//	def scaffold = Agent

	//index page
    def index () {}
	
	//login page
	def login () {}
	
	//register page
	def register () {
		def username = params.username
		def password = params.password
		def name = params.name
		def role = params.role
		
		Date date = new Date()
		
		//params are valid, create a new agent
		if (username && password && name && role) {
			Agent agent = new Agent(
				username:username,
				password:password,
				name:name,
				role:role,
				dateCreated:date,
				dateUpdated:date)
			
			//user creation with error
			if (!agent.save(flush:true)) {
				println "User creation with error."
				agent.errors.each {
					println it
				}
			}
			//user created successfully
			else {
				println "New agent created sucessfully!"
				session.user = agent
				redirect(controller:"agent", action:"index")
			}
		}
		//params are not valid
		else {
			println "Some parameters are missing when creating user."
		}
	}
	
	//validate the user & login
	def authenticate () {
	  def username = params.username
	  def password = params.password
	  
	  //find valid user, login
	  if (verifyUser(username, password)) {
		def user = Agent.findByUsernameAndPassword(username, password)
		println "${user.name} login!"
		  
		//login
		session.user = user
		redirect(controller:"agent",action:"index")
	  }
	  //username and password combo is invalid, login again
	  else {
		flash.message = "Sorry, ${params.username}. Your information is invalid. Please try again."
		println ("Sorry, ${params.username}. Your information is invalid. Please try again.")
		redirect(controller:"agent", action:"login")
	  }
	}
	
	//verify user is valid or not
	def verifyUser (def username, def password) {
		def user = Agent.findByUsernameAndPassword(username, password)
		
		//find valid user, login
		if (user) {
		  return true
		}
		//username and password combo is invalid, login again
		else {
		  return false
		}
	}
	
	//logout current user
	def logout () {
	  def user = session.user
	  if (user) {
		  println "${user.name} logout!"
			
		  flash.message = "${user.name} logout!"
		  //logout
		  session.user = null
		  //clear other session info
		  session.targetDataset = null
		  session.showDatasetName = null
		  session.config = null
		  
		  redirect(controller:"agent", action:"login")
	  }
	}
	
	//show user's profile
	def profile () {
		def user = session.user
		
		if (user) {
			def curUser = Agent.findByUsernameAndPassword(user.username, user.password)
			
			if (curUser) {
				[user: curUser]
			}
			//invalid user info
			else {
				println("invalid user info.")
				flash.message = "invalid user info."
			}
		}
		//user does not login
		else {
			println ("Please login first.")
			render(view: "/loginWarning.gsp")
		}
	}
}
