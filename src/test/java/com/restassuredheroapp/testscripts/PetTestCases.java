package com.restassuredheroapp.testscripts;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.restassuredheroapp.pojos.Category;
import com.restassuredheroapp.pojos.Error;
import com.restassuredheroapp.pojos.PetStore;
import com.restassuredheroapp.pojos.PhotoUrls;
import com.restassuredheroapp.pojos.Tags;
import com.restassuredheroapp.utils.FakerData;
import com.restassuredheroapp.utils.SpecificationBuilder;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

import java.util.LinkedList;
import java.util.List;

public class PetTestCases {

	PetStore deSerialized;
	PetStore serialized;

	@DataProvider(name = "testdata")
	public Object[][] TestData() {
		return new Object[][] {

				{ "!@#!$%^^&&))+_" }, { "1231131232" }, { "doggie" } };
	}

	@Test(dataProvider = "testdata")
	public void CreatePet(String PetName) {

		PetStore petStore = new PetStore();
		serialized = petStore.GetPayload(PetName);

		Response response = given().spec(SpecificationBuilder.GetRequestSpec()).queryParam("api_key", "special-key")
				.body(serialized).when().post("/pet").then().spec(SpecificationBuilder.GetResponseSpec()).extract()
				.response();

		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.getContentType(), "application/json");

		deSerialized = response.as(PetStore.class);

		Assert.assertEquals(deSerialized.getName(), serialized.getName());
		Assert.assertEquals(response.getContentType(), "application/json");
	}

	@Test(dependsOnMethods = "CreatePet")
	public void CreatePetwithDifferentHTTPMethod() {

		Response response = given().spec(SpecificationBuilder.GetRequestSpec()).queryParam("api_key", "special-key")
				.body(serialized).when().get("/pet").then().spec(SpecificationBuilder.GetResponseSpec()).extract()
				.response();

		Assert.assertEquals(response.getStatusCode(), 405);
		Assert.assertEquals(response.getContentType(), "application/xml");

		Assert.assertTrue(response.getStatusLine().contains("Method Not Allowed"));

	}

	@Test(dependsOnMethods = "CreatePet")
	public void CreatePetValidateJsonSchema() {

		String JsonSchema = "./JsonSchema/CreatePet.json";

		given().spec(SpecificationBuilder.GetRequestSpec()).queryParam("api_key", "special-key").body(serialized).when()
				.post("/pet").then().spec(SpecificationBuilder.GetResponseSpec()).assertThat()
				.body(matchesJsonSchemaInClasspath(JsonSchema));

	}

	@Test(dependsOnMethods = "CreatePet")
	public void GetPet() {
		Response response = given().spec(SpecificationBuilder.GetRequestSpec()).queryParam("api_key", "special-key")
				.when().get("/pet" + "/" + deSerialized.getId()).then().spec(SpecificationBuilder.GetResponseSpec())
				.extract().response();

		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.getContentType(), "application/json");

		deSerialized = response.as(PetStore.class);

		Assert.assertEquals(deSerialized.getName(), serialized.getName());

	}

	@Test(dependsOnMethods = "GetPet")
	public void GetPetWithInvalidIDAsInteger() {
		Response response = given().spec(SpecificationBuilder.GetRequestSpec()).queryParam("api_key", "special-key")
				.when().get("/pet" + "/" + 123456).then().spec(SpecificationBuilder.GetResponseSpec()).extract()
				.response();

		Assert.assertEquals(response.getStatusCode(), 404);
		Assert.assertEquals(response.getContentType(), "application/json");

		Error errordeSerialized = response.as(Error.class);

		Assert.assertEquals(errordeSerialized.getMessage(), "Pet not found");
		Assert.assertEquals(errordeSerialized.getCode(), 1);
		Assert.assertEquals(errordeSerialized.getType(), "error");

		Assert.assertTrue(response.getStatusLine().contains("Not Found"));

	}

	@Test(dependsOnMethods = "GetPet")
	public void GetPetWithInvalidIDWithAsString() {
		Response response = given().spec(SpecificationBuilder.GetRequestSpec()).queryParam("api_key", "special-key")
				.when().get("/pet" + "/" + "QWERTY").then().spec(SpecificationBuilder.GetResponseSpec()).extract()
				.response();

		Assert.assertEquals(response.getStatusCode(), 404);
		Assert.assertEquals(response.getContentType(), "application/json");

		Error errordeSerialized = response.as(Error.class);

		Assert.assertTrue(errordeSerialized.getMessage().contains("java.lang.NumberFormatException"));
		Assert.assertEquals(errordeSerialized.getCode(), 404);
		Assert.assertEquals(errordeSerialized.getType(), "unknown");

		Assert.assertTrue(response.getStatusLine().contains("Not Found"));

	}

	@Test(dependsOnMethods = "GetPet")
	public void UpdatedPet() {

		PetStore petStore = new PetStore();
		PetStore updatedSerialized = petStore.GetPayload("bhgad Billa");

		Response response = given().spec(SpecificationBuilder.GetRequestSpec()).queryParam("api_key", "special-key")
				.body(updatedSerialized).when().put("/pet").then().spec(SpecificationBuilder.GetResponseSpec())
				.extract().response();

		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.getContentType(), "application/json");

		deSerialized = response.as(PetStore.class);

		Assert.assertEquals(deSerialized.getName(), updatedSerialized.getName());

	}

	@Test(dependsOnMethods = "UpdatedPet")
	public void DeletePet() {
		Response response = given().spec(SpecificationBuilder.GetRequestSpec()).queryParam("api_key", "special-key")
				.when().delete("/pet" + "/" + deSerialized.getId()).then().spec(SpecificationBuilder.GetResponseSpec())
				.extract().response();

		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.getContentType(), "application/json");

		Error errorPetDeserialized = response.as(Error.class);

		Assert.assertEquals(errorPetDeserialized.getCode(), 200);
		Assert.assertEquals(errorPetDeserialized.getType(), "unknown");
		Assert.assertEquals(errorPetDeserialized.getMessage(), String.valueOf(deSerialized.getId()));

	}

	@Test(dependsOnMethods = "DeletePet")
	public void DeletePetWithInvalidIDAsInteger() {
		Response response = given().spec(SpecificationBuilder.GetRequestSpec()).queryParam("api_key", "special-key")
				.when().delete("/pet" + "/" + 121212).then().spec(SpecificationBuilder.GetResponseSpec()).extract()
				.response();

		Assert.assertEquals(response.getStatusCode(), 404);
		Assert.assertTrue(response.getStatusLine().contains("Not Found"));

	}

	@Test(dependsOnMethods = "DeletePet")
	public void DeletePetWithInvalidIDWithAsString() {
		Response response = given().spec(SpecificationBuilder.GetRequestSpec()).queryParam("api_key", "special-key")
				.when().delete("/pet" + "/" + "QWERTY").then().spec(SpecificationBuilder.GetResponseSpec()).extract()
				.response();

		Assert.assertEquals(response.getStatusCode(), 404);
		Assert.assertEquals(response.getContentType(), "application/json");

		Error errordeSerialized = response.as(Error.class);

		Assert.assertTrue(errordeSerialized.getMessage().contains("java.lang.NumberFormatException"));
		Assert.assertEquals(errordeSerialized.getCode(), 404);
		Assert.assertEquals(errordeSerialized.getType(), "unknown");

		Assert.assertTrue(response.getStatusLine().contains("Not Found"));

	}

}
