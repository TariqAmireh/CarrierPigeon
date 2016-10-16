var express = require('express');
var bodyParser = require('body-parser');
var app = express();
var output;
var Twitter = require('mtwitter');

var listener = app.listen(process.env.PORT, function () {
  console.log('Your app is listening on port ' + listener.address().port);
});

var client = new Twitter ({
  consumer_key: '7YsxA8FsW0YgLJitnaeux81V1',
  consumer_secret: 'r3HexuMnSOXiys66L5Y46N8D11B2xhzg6Ih2bjBoDOPgeEehWu',
  access_token_key: '772887561140174848-sFIPHxRohUZZDcRck6xNtH1TUW2MAVw',
  access_token_secret: 'JOhrCrN6WI721k9TOp2EstrOszRaQRnRNuo75HDHJwuuf'
});

app.use(bodyParser.urlencoded({extended: false}));

app.post("/message", function (request, response) {
  console.log(request.body);
  var input = request.body.Body;
  client.get('search/tweets', {q: input, count: 1}, function(error, tweets) {
    if (error) throw error;
    output = tweets;
    console.log(output);
  });
  console.log(output.statuses[0].text);
  response.send("<Response><Message>"+ output.statuses[0].text +"</Message></Response>")
});

app.get("/", function (request, response) {
  response.sendFile(__dirname + '/views/index.html');
});
