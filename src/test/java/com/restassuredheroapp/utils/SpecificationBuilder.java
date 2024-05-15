package com.restassuredheroapp.utils;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecificationBuilder {

	public static RequestSpecification GetRequestSpec() {
		return new RequestSpecBuilder().setBaseUri("https://petstore.swagger.io/v2").setContentType(ContentType.JSON)
				.addFilter(new AllureRestAssured()).log(LogDetail.ALL).build();
		
		
		
		
	}
	
	
	
	

	public static ResponseSpecification GetResponseSpec() {
		return new ResponseSpecBuilder().log(LogDetail.ALL).build();
	}
	

	
	public static RequestSpecification GetRequestSpecHero() {
		return new RequestSpecBuilder().setBaseUri("https://hmcl-funapp-dxlint-oneapp-uat-ci-01t.azurewebsites.net/api/").setContentType(ContentType.JSON)
				.addFilter(new AllureRestAssured()).log(LogDetail.ALL).build();
	}

	public static ResponseSpecification GetResponseSpecHero() {
		return new ResponseSpecBuilder().log(LogDetail.ALL).build();
	}
	
}
