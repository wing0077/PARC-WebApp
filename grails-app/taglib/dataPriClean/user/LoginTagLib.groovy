package dataPriClean.user

class LoginTagLib {
    static defaultEncodeAs = [taglib:'html']
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]
	
	def loginControl = {
		if(session.user){
		  out << "Hello ${session.user.name}"
		  out << """[${link(action:"logout", controller:"agent"){"Logout"}}]"""
	    } else {
	      out << "[${link(action:"login", controller:"agent"){"Login"}}]"   
		}
	}
}
