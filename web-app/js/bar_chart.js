//var dataset = {data: [{items: '1', values: 22},
//		{items: '2', values: 1},
//		{items: '5', values: 3}]};

//dataset = {data: [{items: r1, values:1.254947835079646},{items: r2, values:1.254947835079646},{items: r3, values: 0.627473917539823},]}

var margin = {top: 5, right: 20, bottom: 30, left: 65},
    width = 365 - margin.left - margin.right,
    height = 200 - margin.top - margin.bottom
    
// Color scale
var color = d3.scale.category20();

var x = d3.scale.ordinal()
    .rangeRoundBands([0, width], .1);

var y = d3.scale.linear()
    .range([height, 0]);

var xAxis = d3.svg.axis()
    .scale(x)
    .orient("bottom");

var yAxis = d3.svg.axis()
    .scale(y)
    .orient("left")
    .ticks(5);

//var svg = d3.select("#pvt")
var svg = d3.select(select)
    .attr("width", width + margin.left + margin.right)
    .attr("height", height + margin.top + margin.bottom)
    .append("g")
    .attr("transform", "translate(" + margin.left + "," + margin.top + ")");

x.domain(dataset.data.map(function(d) { return d.items; }));
var maxV = d3.max(dataset.data, function(d) { return d.values; });
var minV = d3.min(dataset.data, function(d) { return d.values; });
var base
if (maxV > minV && (minV > 0.25 * (maxV - minV))) {
	base = minV - 0.25 * (maxV - minV);
}
else if (maxV == minV) {
	base = 0
}
else {
	base = minV
}
y.domain([base, maxV]);
  
svg.append("g")
  .attr("class", "x axis")
  .attr("transform", "translate(0," + height + ")")
  .call(xAxis);

svg.append("g")
  .attr("class", "y axis")
  .call(yAxis)
  .append("text")
  .attr("transform", "translate(-80,90)rotate(270)")
  .attr("y", 15)
  .attr("dy", ".71em")
//	      .style("text-anchor", "end")
  .style("font-size", "20px")
  .text(title);
  
svg.append("g")
  .attr("class", "y axis")
  .call(yAxis)
  .append("text")
  .attr("transform", "translate(-20,163)")
  .attr("y", 10)
  .attr("dy", ".71em")
//      .style("text-anchor", "end")
  .style("font-size", "15px")
  .text("Repair");

svg.selectAll(".bar")
	.data(dataset.data)
	.enter().append("rect")
	.attr("class", "bar")
	.attr("x", function(d) { return x(d.items); })
	.attr("width", x.rangeBand())
	.attr("y", function(d) { return y(d.values); })
	.attr("height", function(d) { return height - y(d.values); })
	.attr("fill", function(d,i) { return color(i % dataset.data.length); });
