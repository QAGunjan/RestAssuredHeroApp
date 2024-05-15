package com.restassuredheroapp.utils;

import static io.restassured.RestAssured.given;

import org.hamcrest.Matcher;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class GenericHttpsMethods {
	
	public static Response post(String endPoint, Object requestLoginRoot) {
		return given().spec(SpecificationBuilder.GetRequestSpecHero()).body(requestLoginRoot).when().post(endPoint).then()
				.spec(SpecificationBuilder.GetResponseSpecHero()).extract().response();
	}
	
	
	public static ValidatableResponse post(String endPoint, Object requestLoginRoot, String JsonSchema) {
		
		return given().spec(SpecificationBuilder.GetRequestSpec()).body(requestLoginRoot).when().post(endPoint).then()
				.spec(SpecificationBuilder.GetResponseSpec()).assertThat().body(matchesJsonSchemaInClasspath(JsonSchema));
	}
}
