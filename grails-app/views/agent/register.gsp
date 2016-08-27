<html>
  <head>
    <meta name="layout" content="main" />
    <title>Register</title>         
  </head>
  <body>
      <h1>Register</h1>
      <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
      </g:if>
      
      <g:form class="form-horizontal" controller="agent" action="register" method="post">
		  <div class="form-group">
		    <label class="col-sm-2 control-label">Username*</label>
		    <div class="col-sm-4">
		      <input type="text" class="form-control" id="username" name="username" placeholder="Username" required>
		    </div>
		  </div>
		  <div class="form-group">
		    <label class="col-sm-2 control-label">Password*</label>
		    <div class="col-sm-4">
		      <input type="password" class="form-control" id="password" name="password" placeholder="Password" required>
		    </div>
		  </div>
		  <div class="form-group">
		    <label class="col-sm-2 control-label">Name*</label>
		    <div class="col-sm-4">
		      <input type="text" class="form-control" id="name" name="name" placeholder="Name" required>
		    </div>
		  </div>
		  <div class="form-group">
		    <label class="col-sm-2 control-label">Role*</label>
		    <div class="col-sm-4">
			    <select class="form-control" name="role" required>
			        <option value="Target">Target</option>
				    <option value="Master">Master</option>
			    </select>
		    </div>
		  </div>
		  <div class="form-group">
		    <div class="col-sm-offset-2 col-sm-10">
		      <button type="submit" class="btn btn-success">Register</button>
		    </div>
		  </div>
		</g:form>
      
  </body>
</html>