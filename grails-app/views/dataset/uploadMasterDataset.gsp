<html>
  <head>
    <meta name="layout" content="main" />
    <title>Upload Dataset</title>         
  </head>
  <body>
  	<g:render template="/navigates/navigates_ManageDatasets" />
  
   	<h1>Upload your datasets and define your constraints</h1>
   	
   	<br>
   	<br>
   	
	<g:uploadForm controller="dataset" action="uploadData">
        <div class="form-group">
		    <label>Master dataset</label>
		    <input type="file" name="dataFile" />
		    <p class="help-block">File should be .csv format.</p>
	    </div>
        <br>
        
        <div class="form-group">
		    <!-- <label>Constraint File</label> -->
		    <input type="file" name="conFile" />
		    <p class="help-block">Define functional dependencies (FDs). FDs can be specified as a list of comma separated attributes, where the last attribute represents the RHS of the FD. Define each FD on a single line.</p>
	    </div>
        <br>
        
	    <div class="form-group">
        	<p><input type="submit" class="btn btn-success" value="Upload"/></p>
       	</div>
    </g:uploadForm>
    <br>
    <g:if test="${flash.message}">
  		<p>${flash.message}</p>
  	</g:if>
  </body>
</html>