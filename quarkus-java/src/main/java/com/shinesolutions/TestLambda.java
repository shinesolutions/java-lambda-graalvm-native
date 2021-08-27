package com.shinesolutions;

import javax.inject.Inject;
import javax.inject.Named;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

@Named("test")
public class TestLambda implements RequestHandler<Map<String, String>, String> {

    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Inject
    ProcessingService service;

    @Override
    public OutputObject handleRequest(Map<String, String> input, Context context) {
        return service.process(toinput).setRequestId(context.getAwsRequestId());
    }
}
