<html>
  <head>
    <meta name="layout" content="main" />
    <title>Login</title>         
  </head>
  <body>
      <h1>Login</h1>
      <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
      </g:if>
      
      <g:form class="form-horizontal" controller="agent" action="authenticate" method="post">
		  <div class="form-group">
		    <label class="col-sm-2 control-label">Username</label>
		    <div class="col-sm-4">
		      <input type="text" class="form-control" name="username" placeholder="Username">
		    </div>
		  </div>
		  <div class="form-group">
		    <label class="col-sm-2 control-label">Password</label>
		    <div class="col-sm-4">
		      <input type="password" class="form-control" name="password" placeholder="Password">
		    </div>
		  </div>
		  <div class="form-group">
		    <div class="col-sm-offset-2 col-sm-10">
		      <div class="checkbox">
		        <label>
		          <input type="checkbox"> Remember me
		        </label>
		      </div>
		    </div>
		  </div>
		  <div class="form-group">
		    <div class="col-sm-offset-2 col-sm-10">
		      <button type="submit" class="btn btn-success">Login</button>
		      <g:link action="register" class="btn btn-danger">Register here</g:link>
		    </div>
		  </div>
		</g:form>
      
  </body>
</html>