'use strict';

module.exports.handler = async (event, context) => {

  let greeting = '';
  let name = '';

  if (event.body) {
    let body = JSON.parse(event.body);
    if (body.greeting) {
      greeting = body.greeting;
    }
    if (body.name) {
      name = body.name;
    }
  }

  if (name == 'Stuart') {
    return {
      statusCode: 400,
      body: JSON.stringify(
          {
            message: "Can only greet nicknames"
          }
      )
    };
  }
  
  return {
    statusCode: 200,
    body: JSON.stringify(
      {
        result: "${greeting} ${name}",
        requestId: context.awsRequestId
      },
      null,
      2
    ),
  };
};

