<html>
  <head>
    <meta name="layout" content="main" />
    <title>Dataset</title>         
  </head>
  <body>
    	<g:if test="${session.user}">
    		<g:render template="/navigates/navigates_ManageDatasets" />
			
    		<h1>Dataset:</h1>
    		
    		<br>
    		<p>Dataset Name: ${dataset["datasetName"] }</p>
    		<p>Constraint Name: ${dataset["conName"] }</p>
    		<br>
    		
    		<table class="table table-hover">
			  <tr class="active">
			  	<td>Record #</td>
			  	<g:each in="${dataset["attrs"] }" var="attr">
				    <td>${attr }</td> 
			    </g:each>
			  </tr>
			  <g:each in="${dataset["data"]}" var="dataRow" >
			  		<tr>
			  			<g:each in="${dataRow }" var="dataCol">
			  				<td>${dataCol }</td>
			  			</g:each>
                	</tr>
               </g:each>
			</table>
			
    	</g:if>
    	<g:else>
    		<h1>Please login first!</h1>
    	</g:else>
    	
    	<g:if test="${flash.message}">
        	<div class="message">${flash.message}</div>
      	</g:if>
      
  </body>
</html>