<html>
  <head>
    <meta name="layout" content="main" />
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'range.css')}" type="text/css">
    <title>Lexical Simulated Annealing Advanced Setting</title>         
  </head>
  <body>
	  	<g:render template="/navigates/navigates_CleanData" />
	
    	<h3>Lexical Search Advanced Settings</h3>
    	<br>
    	
		<g:uploadForm controller="dataCleaning" action="lexicalSASetting" method="POST">
			<g:render template="/dataCleaning/searchConfigSetting" />
		
			<div class="panel panel-success">
				<div class="panel-heading">Privacy & Cleaning Utility Objective Configuration:</div>
				<div class="panel-body">
					<div class="row">
      					<div class="col-xs-6">
					        <label>Privacy:</label> 
					        <div class="range range-success">
					        	<input id="privacy" type="range" name="privacy" min="0.0" max="1.0" value="${config["privacy"] }" step="0.1" onchange="privacyText.value=value" />
					        	<output id="privacyText">${config["privacy"] }</output>
					        </div>
				        </div>
			        
			        	<div class="col-xs-6">
					        <label>Cleaning:</label> 
					        <div class="range range-success">
					        	<input id="cleaning" type="range" name="cleaning" min="0.0" max="1.0" value="${config["cleaning"] }" step="0.1" onchange="cleaningText.value=value" />
					        	<output id="cleaningText">${config["cleaning"] }</output>
					        </div>
				        </div>
			        </div>
		        </div>
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