<html>
  <head>
    <meta name="layout" content="main" />
    <title>Privacy Preserving Data Cleaning</title>         
  </head>
  <body>
  		<div class="container">
		    <div class="jumbotron">
	            <h2>PARC: Privacy-AwaRe data Cleaning</h2>
          	</div>
        </div>
  
    	<div class="container">
	      <!-- Example row of columns -->
	      <div class="row">
	        <div class="col-md-4">
	         <div class="panel panel-primary">
			  <div class="panel-heading">
			    <h2>
			    1. Manage Datasets
			    </h2>
			  </div>
			  <div class="panel-body">
			  	Manage the input datasets. Define your functional dependencies.
			  	<br>
			  	<br>
			  	<button type="button" onclick="location.href='dataset'" class="btn btn-primary">Go</button>
			  </div>
			</div>
	        </div>
	        <div class="col-md-4">
	        	<div class="panel panel-success">
				  <div class="panel-heading">
				    <h2>
				    2. Identify Errors
				    </h2>
				  </div>
				  <div class="panel-body">
				  	Check the quality of your data. Automatically detect errors with respect to your data quality rules.
				  	<br>
				  	<br>
				  	<button type="button" onclick="location.href='datasetSelectionQua'" class="btn btn-success">Go</button>
				  </div>
				</div>
	       </div>
	        <div class="col-md-4">
	        	<div class="panel panel-info">
				  <div class="panel-heading">
				    <h2>
				    3. Clean Datasets
				    </h2>
				  </div>
				  <div class="panel-body">
				  	Correct your data errors while maintaining the privacy of your data values.
				  	<br>
				  	<br>
				  	<button type="button" onclick="location.href='datasetSelectionClean'" class="btn btn-info">Go</button>
				  </div>
				</div>
	        </div>
	      </div>
	    </div> <!-- /container -->
  </body>
</html>