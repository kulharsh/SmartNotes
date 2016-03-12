var express = require('express');
var http = require('http');
var app = express();
var striptags = require('striptags');
var escapehtml = require('escape-html');
var urlencode = require('urlencode');
var search = {
   "term" : "james"
}

app.get('/search', function (req, res) {
   var search = req.query.q;
   var clean = (striptags(search));
   var c = urlencode(clean);
   console.log(c);
   http.get({
  hostname: 'localhost',
  port: 8983,
  path: '/solr/SN_trial/select?wt=json&q='+c,
  agent: false  // create a new agent just for this one request
}, function (solrRes) {
	var stringData = '';
  solrRes.on('data', function( data ) {
        stringData += data;
    } );
	solrRes.on('end',function(data){
		res.writeHead(200, {'Content-Type': 'text/plain'});
		object = JSON.parse(stringData);
		res.write(JSON.stringify(object.response.numFound));
		object.response.docs.forEach(function(value, key){
				res.write(JSON.stringify(value.attr_stream_source_info));
		})
		res.end('');
	})
})
 
})

var server = app.listen(8081, function () {

  var host = server.address().address
  var port = server.address().port
  console.log("Example app listening at http://%s:%s", host, port);

})