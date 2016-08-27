<html>
  <head>
    <meta name="layout" content="main" />
    
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'multi_bars.css')}" type="text/css">
    
    <script src="http://d3js.org/d3.v3.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    
    <script type="text/javascript">
	$(document).ready(function(){ 
	    $("#myTab a").click(function(e){
	    	e.preventDefault();
	    	$(this).tab('show');
	    });
	    $("#myTab-weighted").tab('show');
	});
	</script>
    
    <title>Data Cleaning</title>   
    
  </head>
  <body>
  	<g:render template="/dataCleaning/recommendationHeader" />
  
    <h3>Recommended Repairs:</h3>
    <div class="col-md-8">
   	
		   	<ul class="nav nav-tabs" id="myTab">
		      <li><a id="myTab-weighted" href="#weighted"><font size="3"><b>Weighted Search</b></font></a></li>
			  <li><a id="myTab-constrained" href="#constrained"><font size="3"><b>Constrained Search</b></font></a></li>
			  <li><a id="myTab-dynamic" href="#dynamic"><font size="3"><b>Dynamic Search</b></font></a></li>
			  <li><a id="myTab-lexical" href="#lexical"><font size="3"><b>Hierarchical Search</b></font></a></li>
			</ul>
		   	
		   	<div class="tab-content">
		   		<g:set var="searchCounter" value="${0}" />
			   	<g:each in="${recs}" var="recBySearch" >
			   		<g:set var="searchCounter" value="${searchCounter + 1}" />
			   		<div id="${recBySearch["search"] }" class="tab-pane fade">
			   		
				   		<div class="panel panel-default">
				  			<div class="panel-body">
				  				<div class="panel panel-default">
								  <div class="panel-body">
								  	<div class="col-md-6">
									    <b>Master Dataset: ${recBySearch["masterDataset"] } </b><br>
						   				<b>Target Dataset: ${recBySearch["targetDataset"] } </b><br>
						   				<b>Similarity Threshold (&#964): ${recBySearch["simThreshold"] }</b>
					   				</div>
					   				<div class="col-md-6">
					   					<b>Privacy Loss (pvt): ${recBySearch["searchConfig"]["alphaPvt"] }</b><br>
						   				<b>Data Utility (util): ${recBySearch["searchConfig"]["betaInd"] }</b><br>
						   				<b># Updates (upd): ${recBySearch["searchConfig"]["gamaSize"] }</b>
					   				</div>
								  </div>
								</div>
				  			
				  				<g:set var="fdCounter" value="${0}" />
				  				<g:each in="${recBySearch["recommendation"] }" var="rec">
				  				<g:set var="fdCounter" value="${fdCounter + 1}" />
				  					<div class="panel panel-success">
				  						<div class="panel-heading"><font size="3"><b>F${fdCounter }: [ ${rec["constraint"]["antecedent"] } ] &#8594; ${rec["constraint"]["consequent"] }<span class="pull-right"><g:link controller="dataCleaning" action="objectiveScores" params="[constraintId: "${fdCounter }", searchId:"${searchCounter }" ]">Details</g:link></span></b></font></div>
				  						<div class="panel-body">
		  								<g:set var="counter" value="${0}" />
		  									<g:each in="${rec["recContent"] }" var="candidate" >
		  									<g:set var="counter" value="${counter + 1}" />
		  										<g:if test="${(counter % 2) == 1}">
			  									<div class="row">
			  									</g:if>
			  										<div class="col-sm-1 col-md-1">
			  											<h4 class="text-center"><g:link controller="dataCleaning" action="recommendationDetails" params="[constraintId: "${fdCounter }", repairId: "${counter }", searchId:"${searchCounter }" ]">r${counter }</g:link></h4>
			  										</div>
												   	<div class="col-sm-5 col-md-5">
												   		<svg class="chart" id="chart${fdCounter }${counter }"></svg>
												   	</div>
											    	<script>
											    	var select = "#chart${fdCounter }${counter }"
											    	var data = {labels: [''],
										    			         series: [
										    			           {
										    			             label: 'pvt',
										    			             values: ["${candidate["pvt"]}"]
										    			           },
										    			           {
										    			             label: 'util',
										    			             values: ["${candidate["ind"]}"]
										    			           },
										    			           {
										    			             label: 'upd',
										    			             values: ["${candidate["changes"]}"]
										    			           },]};
											    	</script>
										  			<script src="${resource(dir: 'js', file: 'multi_bars.js')}"></script>
										  		<g:if test="${(counter % 2) == 0}">
									  			</div>
									  			</g:if>
										   	</g:each>
									   	</div>
								   	</div>
							   	</g:each>
						   	</div>
					   	</div>
				   	</div>
			   	</g:each>
   			</div>
		</div>
   	
	<g:if test="${flash.message}">
  		<p>${flash.message}</p>
  	</g:if>
  	
  	<!-- 
	  	<svg class="chart"></svg>
	  	<script src="${resource(dir: 'js', file: 'multi_bars.js')}"></script>
  	 -->
  	
  </body>
</html>