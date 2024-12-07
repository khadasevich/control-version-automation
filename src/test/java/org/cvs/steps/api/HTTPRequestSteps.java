package org.cvs.steps.api;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.config.LogConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.Method;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class HTTPRequestSteps {
    
    public static ValidatableResponse get(RequestSpecification reqSpec) {
        return executeHttpRequest(reqSpec, Method.GET);
    }

    public static ValidatableResponse post(RequestSpecification reqSpec) {
        return executeHttpRequest(reqSpec, Method.POST);
    }

    public static ValidatableResponse put(RequestSpecification reqSpec) {
        return executeHttpRequest(reqSpec, Method.PUT);
    }

    public static ValidatableResponse delete(RequestSpecification reqSpec) {
        return executeHttpRequest(reqSpec, Method.DELETE);
    }

    public static ValidatableResponse executeHttpRequest(RequestSpecification reqSpec, Method method) {
        return executeHttpRequest(reqSpec, method, true);
    }

    public static ValidatableResponse executeHttpRequest(RequestSpecification reqSpec, Method method, boolean isAttachResponse) {
        if (isAttachResponse) {
            reqSpec = reqSpec.filter(new AllureRestAssured()
                    .setRequestAttachmentName("Request")
                    .setResponseAttachmentName("Response"));
        }
        return RestAssured
                .given(reqSpec)
                .config(RestAssuredConfig
                        .config()
                        .logConfig(LogConfig
                                .logConfig()
                                .blacklistHeader("Authorization")))
                .relaxedHTTPSValidation()
                .when()
                .request(method)
                .then();
    }
}
