package com.shinesolutions.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.shinesolutions.domain.InputObject;
import com.shinesolutions.domain.OutputObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Handler for requests to Lambda function.
 */
public class App implements RequestHandler<Map<String, String>, String> {

    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    ProcessingService service = new ProcessingService();

    public String handleRequest(final Map<String, String> input, final Context context) {

        LambdaLogger logger = context.getLogger();

        logger.log("INPUT: " + input);

        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("X-Custom-Header", "application/json");

        String inputJson = gson.toJson(input);
        InputObject inputObject = gson.fromJson(inputJson, InputObject.class);
        logger.log(inputObject.toString());

        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent()
                .withHeaders(headers);
        try {
            OutputObject outputObject = service.process(inputObject).setRequestId(context.getAwsRequestId());

            return gson.toJson(outputObject);
        } catch (IllegalArgumentException e) {
            return gson.toJson(e.getMessage());
        }
    }
}
