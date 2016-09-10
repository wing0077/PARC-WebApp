<html>
  <head>
    <meta name="layout" content="main" />
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'range.css')}" type="text/css">
    <title>Constrained Simulated Annealing Advanced Setting</title>         
  </head>
  <body>
	 	<g:render template="/navigates/navigates_CleanData" />
		
		<h3>Constrained Search Advanced Settings</h3>
    	<br>
    	
		<g:uploadForm controller="dataCleaning" action="constrainedSASetting" method="POST">
			<g:render template="/dataCleaning/searchConfigSetting" /> 
			<div class="panel panel-success">
				<div class="panel-heading">Privacy & Cleaning Utility Objective Configuration:</div>
				<div class="panel-body">
				
					<div class="row">
			        	<div class="col-xs-6">
					        <label>Cleaning:</label> 
					        <div class="range range-success">
					        	<input id="cleaning" type="range" name="cleaning" min="0.0" max="1.0" value="${config["cleaning"] }" step="0.1" onchange="cleaningText.value=value" />
					        	<output id="cleaningText">${config["cleaning"] }</output>
					        </div>
				        </div>
				        
				        <div class="col-xs-6">
					        <label>Size:</label> 
					        <div class="range range-success">
					        	<input id="size" type="range" name="size" min="0.0" max="1.0" value="${config["size"] }" step="0.1" onchange="sizeText.value=value" />
					        	<output id="sizeText">${config["size"] }</output>
					        </div>
				        </div>				        
			        </div>
			        
			        <%--
			        
			    	<div class="row">
      					<div class="col-xs-4">
					        <div class="range range-success">
					        	<input id="alphaPvt" type="range" name="alphaPvt" min="0.0" max="1.0" value="${config["alphaPvt"] }" step="0.001" onchange="alphaPvtText.value=value" />
					        	<output id="alphaPvtText">${config["alphaPvt"] }</output>
					        </div>
					         Privacy loss
				        </div>
			        
			        	<div class="col-xs-4">
					        <div class="range range-success">
					        	<input id="betaInd" type="range" name="betaInd" min="0.0" max="1.0" value="${config["betaInd"] }" step="0.001" onchange="betaIndText.value=value" />
					        	<output id="betaIndText">${config["betaInd"] }</output>
					        </div>
					        Data utility
				        </div>
				        
				        <div class="col-xs-4">
					        <div class="range range-success">
					        	<input id="gamaSize" type="range" name="gamaSize" min="0.0" max="1.0" value="0.034" step="0.001" onchange="gamaSizeText.value=value" />
					        	<output id="gamaSizeText">${config["gamaSize"] }</output>
					        </div>
					        Number of updates
				        </div>
			        </div>    
			        
			        
		        --%></div>
	        </div>

	        <input class="btn btn-success" name="func" type="submit" value="Save"/>
	        <input class="btn btn-primary" name="func" type="submit" value="Reset to default"/>
			
	    </g:uploadForm>
	    <br>
	    <g:if test="${flash.message}">
	  		<p>${flash.message}</p>
	  	</g:if>	
  </body>
</html>