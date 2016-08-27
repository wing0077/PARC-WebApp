<html>
  <head>
    <meta name="layout" content="main" />
    
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    
    <title>Data Cleaning</title>   
    
  </head>
  <body>
  	<g:render template="/dataCleaning/recommendationHeader" />
  	
  	<div class="row">
  		<div class="col-md-8">
    		<h4>Repair Details for r${repairId } <span style="float:right;">F${constraintId }: [ ${constraint["antecedent"] } ] &#8594; ${constraint["consequent"] }</span></h4>
   		</div>
   	</div>
			   		
	<div class="col-md-8">
   		<table class="table table-hover">
   			<tr class="active">
	   			<td><b>Attribute</b></td>
	   			<td><b>Original Value</b></td>
	   			<td><b>Recommended Value</b></td>
	   			<td><b># Repaired Records<b></td>
   			</tr>
   			<%--<tr class="active">
   				<td><p>Record #</p></td>
			    <td><p>Attribute</p></td>
			    <td><p>Change to</p></td>
			  </tr>
	   		--%><%--<g:each in="${recommendationList }" var="record" >
	   			<tr>
	   				<g:each in="${record }" var="recordCol" >
	   					<td><p>${recordCol }</p></td>
   					</g:each>
	   			</tr>
	   		</g:each>
	   	--%>
		   	<g:each in="${recommendations }" var="record" >
	   			<tr>
   					<g:set var="counter" value="${0 }" />
	   				<g:each in="${record }" var="recordCol" >
	   					<g:set var="counter" value="${counter + 1}" />
	   					<g:if test="${counter == 4 }">
	   						<td><g:link controller="dataCleaning" action="repairedRecords" params="[records: "${recordCol }", repairId: "${repairId }"]">${recordCol.size() }</g:link></td>
   						</g:if>
   						<g:elseif test="${counter == 1 }">
   							<td><b><p>${recordCol }</p></b></td>	
   						</g:elseif>
   						<g:else>
   							<td><p>${recordCol }</p></td>
   						</g:else>
   						
   					</g:each>
	   			</tr>
	   		</g:each>
	   	</table>
   	</div>
   	
	<g:if test="${flash.message}">
  		<p>${flash.message}</p>
  	</g:if>
  	
  </body>
</html>