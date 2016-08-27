<html>
  <head>
    <meta name="layout" content="main" />
    
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    
    <title>Data Cleaning</title>   
    
  </head>
  <body>
  	<g:render template="/dataCleaning/recommendationHeader" />
	
    <h3>Repair Records Details for <i>${repairId }</i>:</h3>
   	<br>
			   		
	<div class="col-md-8">
   		<table class="table table-hover">
   			<tr class="active">
   				<td>
   					List of Repaired Target Records:
   				</td>
   			</tr>
   			<tr>
				<td><p>${records }</p></td>
   			</tr>
	   	</table>
   	</div>
   	
	<g:if test="${flash.message}">
  		<p>${flash.message}</p>
  	</g:if>
  	
  </body>
</html>