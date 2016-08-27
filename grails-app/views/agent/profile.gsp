<html>
  <head>
    <meta name="layout" content="main" />
    <title>Profile</title>         
  </head>
  <body>
    	<g:if test="${user}">
    		<h1>Profile:</h1>
    		<br>
    		<label>Name</label>
    		<p>${user.name}</p>
    		<br>
    		<label>Username</label>
    		<p>${user.username}</p>
    		<br>
    		<label>Password</label>
    		<p>${user.password}</p>
    		<br>
    		<label>Role</label>
    		<p>${user.role}</p>
    		<br>
    	</g:if>
    	
    	<g:else>
    		<h1>Please login first!</h1>
    	</g:else>
    	
    	 <g:if test="${flash.message}">
		 	<div class="message">${flash.message}</div>
		 </g:if>
    	
  </body>
</html>