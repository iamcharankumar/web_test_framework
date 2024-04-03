package io.saucelabs.portal.qa.commons.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.dzieciou.testing.curl.CurlRestAssuredConfigFactory;
import com.github.dzieciou.testing.curl.Options;
import com.github.dzieciou.testing.curl.Platform;
import groovy.json.JsonException;
import io.restassured.RestAssured;
import io.restassured.config.RestAssuredConfig;
import io.restassured.response.Response;
import io.saucelabs.portal.qa.exceptions.RestResourceException;
import org.slf4j.event.Level;

public class RestResource {
    private static RestResource instance;

    private final Options OPTIONS = Options.builder().printMultiliner().targetPlatform(Platform.WINDOWS)
            .useShortForm().useLogLevel(Level.INFO).build();
    private final RestAssuredConfig CONFIG = CurlRestAssuredConfigFactory.createConfig(OPTIONS);

    public static RestResource getInstance() {
        if (instance == null) {
            synchronized (RestResource.class) {
                if (instance == null) {
                    instance = new RestResource();
                }
            }
        }
        return instance;
    }

    public <T> T deserialize(Response response, Class<T> classVariable) throws JsonProcessingException {
        return new ObjectMapper().readValue(response.asString(), classVariable);
    }

    public String serialize(Object classObject) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(classObject);
    }

    public Response getApiResponse(String endPoint) {
        Response getResponse = RestAssured.given().config(CONFIG).when().get(endPoint);
        if (getResponse != null)
            return getResponse;
        else
            throw new RestResourceException("GET API Call failed!");
    }

    public Response postApiResponse(String requestBody, String endPoint) throws JsonException {
        Response postResponse = RestAssured.given().contentType(ApiConstants.CONTENT_TYPE).config(CONFIG).body(requestBody).when().post(endPoint);
        if (postResponse != null)
            return postResponse;
        else
            throw new RestResourceException("POST API Call Failed!");
    }
}
