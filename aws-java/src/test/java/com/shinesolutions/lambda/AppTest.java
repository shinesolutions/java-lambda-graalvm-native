package com.shinesolutions.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class AppTest {
    @Test
    public void successfulResponse() {
        RequestHandler<Map<String,String>, String> app = new App();

        Map<String,String> event = new HashMap<>();
        event.put("name", "Bill");
        event.put("greeting", "hello");

        Context context = new TestContext();

        String result = app.handleRequest(event, context);
//        assertEquals(result.getStatusCode().intValue(), 200);
//        assertEquals(result.getHeaders().get("Content-Type"), "application/json");
        assertNotNull(result);
        assertTrue(result.contains("\"hello Bill\""));
    }
}
