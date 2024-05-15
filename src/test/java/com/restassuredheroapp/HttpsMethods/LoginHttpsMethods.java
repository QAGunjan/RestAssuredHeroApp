package com.restassuredheroapp.HttpsMethods;

import static io.restassured.RestAssured.given;

import com.restassuredheroapp.pojos.LoginPojoRoot;
import com.restassuredheroapp.utils.GenericHttpsMethods;
import com.restassuredheroapp.utils.SpecificationBuilder;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public class LoginHttpsMethods {
	
	public static Response post(String endPoint, LoginPojoRoot requestLoginRoot) {
		return GenericHttpsMethods.post(endPoint, requestLoginRoot);
	}

	
	public static ValidatableResponse post(String endPoint, LoginPojoRoot requestLoginRoot, String JsonSchema) {
		return GenericHttpsMethods.post(endPoint, requestLoginRoot, JsonSchema);
	}
}
