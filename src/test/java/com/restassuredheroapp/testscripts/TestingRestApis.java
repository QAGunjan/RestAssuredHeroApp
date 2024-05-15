package com.restassuredheroapp.testscripts;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.restassuredheroapp.pojos.BookStore;
import com.restassuredheroapp.pojos.CollectionOfIsbn;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.json.JSONObject;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class TestingRestApis {

	public static RequestSpecification GetRequestspec() {
		return new RequestSpecBuilder().setBaseUri("https://bookstore.toolsqa.com").setContentType(ContentType.JSON)
				.log(LogDetail.ALL).build();

	}

	public static ResponseSpecification GetResponsespec() {
		return new ResponseSpecBuilder().expectContentType(ContentType.JSON).log(LogDetail.ALL).build();

	}

	@Test
	public void CreateBook() throws JsonProcessingException {

		CollectionOfIsbn collectionOfIsbns_POJO = new CollectionOfIsbn("9781491904244");

		LinkedList<CollectionOfIsbn> ListcollectionOfIsbns_pojo = new LinkedList<CollectionOfIsbn>();
		ListcollectionOfIsbns_pojo.add(collectionOfIsbns_POJO);

		BookStore SerializedObject = new BookStore("9b5f49ab-eea9-45f4-9d66-bcf56a531b85", ListcollectionOfIsbns_pojo);

		Response response = given().spec(TestingRestApis.GetRequestspec()).header("Authorization", "Basic "
				+ "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyTmFtZSI6IlRPT0xTUUEtVGVzdCIsInBhc3N3b3JkIjoiVGVzdEBAMTIzIiwiaWF0IjoxNzAyODAxODcwfQ.4oYaBZuysFKN_QkP6uKHubdnmu7IqXb5uTL9MwB1vL4")
				.body(SerializedObject).when().post("/BookStore/v1/Books").then()
				.spec(TestingRestApis.GetResponsespec()).extract().response();

		BookStore DeserializedObject = response.as(SerializedObject.getClass());

		Assert.assertEquals(response.statusCode(), 201);
		JsonPath jsonPathViewer = response.jsonPath();

		String isbn = jsonPathViewer.get("isbn").toString();

		Assert.assertEquals(DeserializedObject.getCollectionOfIsbns().get(0).getIsbn(), isbn);

		System.out.println("My Actual Data --> " + DeserializedObject.getCollectionOfIsbns().get(0).getIsbn());
		System.out.println("My Expected Data --> " + isbn);

	}

	@Test
	public void GetBooks() {

		Response response = given().spec(TestingRestApis.GetRequestspec()).auth().basic("TOOLSQA-Test", "Test@@123")
				.when().get("/BookStore/v1/Books").then().spec(TestingRestApis.GetResponsespec()).extract().response();

		Assert.assertEquals(response.statusCode(), 200);

		BookStore deserlized = response.as(BookStore.class);

		JsonPath jsonPathViewer = response.jsonPath();
		String titles = jsonPathViewer.get("books[0].title").toString();
		String isbn = jsonPathViewer.get("books[0].isbn").toString();
		SoftAssert sa = new SoftAssert();

		sa.assertEquals(deserlized.getBooks().get(0).getTitle(), titles);

		sa.assertEquals(deserlized.getBooks().get(0).getIsbn(), isbn);

		Headers headers = response.getHeaders();

		String GetHeaderValue = "";
		String GetHeaderName = "";
		for (Header header : headers)

		{
			String Value = header.getValue();
			
			GetHeaderValue = GetHeaderValue + Value;

		}
		String ContentType = response.contentType();
		System.out.println(GetHeaderValue.contains(ContentType));

		sa.assertAll();
	}

}
