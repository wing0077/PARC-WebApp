<html>
  <head>
    <meta name="layout" content="main" />
    <title>Dataset</title>         
  </head>
  <body>
    	<g:if test="${session.user}">
    		<g:render template="/navigates/navigates_ManageDatasets" />
			
    		<h1>Master Datasets:</h1>
    		
    		<br>
    		
    		<table class="table table-hover">
			  <tr class="active">
			    <td>Master Dataset</td> 
			    <td>FDs</td>
			    <td>Operations</td>
			  </tr>
			  <g:each in="${datasets}" var="dataset" >
			  		<tr>
	                    <td><p>${dataset.datasetName}</p></td>
	                    <td><p>${dataset.conName}</p></td>
	                    <td>
		                    <g:form controller="dataset" action="datasetManagement" method="POST">
		                    	<input name="dataset" type="text" value="${dataset.datasetName}" hidden>
		                    	<input name="con" type="text" value="${dataset.conName}" hidden>
		                    	
			                    <input type="submit" class="btn btn-danger" name="func" value="Delete Data">
			                    <input type="submit" class="btn btn-success" name="func" value="Show Dataset">
		                    </g:form>
	                    </td>
                	</tr>
               </g:each>
			</table>
			
			<button type="button" onclick="location.href='uploadMasterDataset'" class="btn btn-success">Upload Dataset</button>
			
    	</g:if>
    	<g:else>
    		<h1>Please login first!</h1>
    	</g:else>
    	
    	<g:if test="${flash.message}">
        	<div class="message">${flash.message}</div>
      	</g:if>
      
  </body>
</html>