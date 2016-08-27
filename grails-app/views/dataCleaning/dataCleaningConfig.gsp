<html>
  <head>
    <meta name="layout" content="main" />
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'range.css')}" type="text/css">
    <title>Data Cleaning Config</title>         
  </head>
  <body>
  	<g:render template="/navigates/navigates_CleanData" />
  
   	<h1>Parameter Configuration</h1>
	<g:uploadForm controller="dataCleaning" action="getRecommendations" method="POST">
        <br>
        <div class="row">
	        <div class="col-xs-6">
		        <label>Similarity Threshold (0 - 1.0):</label> 
		        <div class="range range-success">
		        	<input id="smthRange" type="range" name="simThreshold" min="0.0" max="1.0" value="0.8" step="0.01" onchange="rangeVal.value=value" />
		        	<output id="rangeVal">0.8</output>
		        </div>
	        </div>
        </div>
        <br>
        <div class="row">
	        <div class="col-xs-6">
		        <div class="panel panel-primary">
					<div class="panel-heading">Objective Functions:</div>
					<div class="panel-body">
						<div class="checkbox">
					    	<label>
						      <input type="checkbox" name="searchObj" value="weighted" checked>Weighted Search <g:link controller="dataCleaning" action="weightedSASetting">[Advanced Setting]</g:link>
						    </label>
						</div>
						
						<div class="checkbox">
					    	<label>
						      <input type="checkbox" name="searchObj" value="constrained">Constrained Search <g:link controller="dataCleaning" action="constrainedSASetting">[Advanced Setting]</g:link>
						    </label>
						</div>
						
						<div class="checkbox">
					    	<label>
						      <input type="checkbox" name="searchObj" value="dynamic">Dynamic Search <g:link controller="dataCleaning" action="dynamicSASetting">[Advanced Setting]</g:link>
						    </label>
						</div>
						
						<div class="checkbox">
					    	<label>
						      <input type="checkbox" name="searchObj" value="lexical">Lexical Search <g:link controller="dataCleaning" action="lexicalSASetting">[Advanced Setting]</g:link>
						    </label>
						</div>
						
						<!-- 
				        <p><input type="checkbox" name="searchObj" value="weighted" checked>Weighted Simulated Annealing      <g:link controller="dataCleaning" action="weightedSASetting">[Advanced Setting]</g:link></p>
				        <p><input type="checkbox" name="searchObj" value="constrained">Constrained Simulated Annealing      <g:link controller="dataCleaning" action="constrainedSASetting">[Advanced Setting]</g:link></p>
				        <p><input type="checkbox" name="searchObj" value="dynamic">Dynamic Simulated Annealing      <g:link controller="dataCleaning" action="dynamicSASetting">[Advanced Setting]</g:link></p>
				        <p><input type="checkbox" name="searchObj" value="lexical">Lexical Simulated Annealing      <g:link controller="dataCleaning" action="lexicalSASetting">[Advanced Setting]</g:link></p>
				         -->
			        </div>
		        </div>
	        </div>
        </div>
        <br>
        <p><input type="submit" class="btn btn-success" value="Clean Data"/></p>
    </g:uploadForm>
    <br>
    <g:if test="${flash.message}">
  		<p>${flash.message}</p>
  	</g:if>
	
	<!-- 
	<script>
		showRangeValue();
		function showRangeValue() {
		    var range = document.getElementById("smthRange");
		    var rangeVal = range.value;
		    var rangeValBox = document.getElementById("rangeVal");
		    rangeValBox.innerHTML = rangeVal;
		}
	</script>
	-->

  </body>
</html>