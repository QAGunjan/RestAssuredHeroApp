package com.restassuredheroapp.testscripts;

import org.apache.commons.codec.binary.Base64;
import org.joda.time.DateTime;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import static org.hamcrest.MatcherAssert.assertThat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.restassuredheroapp.HttpsMethods.LoginHttpsMethods;
import com.restassuredheroapp.pojos.LoginPojo;
import com.restassuredheroapp.pojos.LoginPojoRoot;
import com.restassuredheroapp.utils.SpecificationBuilder;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;

public class HeroAppTestRest {

	private RequestSpecification requestspec;
	private ResponseSpecification responsespec;
//	private HashMap<String, Object> mainJsonObj;
	private LoginPojo requestLogin;
	private LoginPojoRoot requestLoginRoot;
	String JsonSchema = "./JsonSchema/loginResponseSchema.json";

//
//		HashMap<String, String> nestedJsonObj = new HashMap<String, String>();
//		nestedJsonObj.put("loginId", "7006523739");
//		nestedJsonObj.put("loginType", "mobile");
//
//		mainJsonObj = new HashMap<String, Object>();
//		mainJsonObj.put("data", nestedJsonObj);

	@Test
	public void ValidateStatusCodeAndMessage() {

//		ObjectMapper objmapper= new ObjectMapper();
//		ObjectNode objNode = objmapper.createObjectNode();  // createObjectNode works same as HashMap

//		HashMap<String, String> nestedJsonObj= new HashMap<String, String>();
//		nestedJsonObj.put("loginId", "7006523739");
//		nestedJsonObj.put("loginType", "mobile");
//		
//		HashMap<String, Object> mainJsonObj= new HashMap<String, Object>();
//		mainJsonObj.put("data", nestedJsonObj);

		LoginBuilder("7006523739", "mobile");
		Response response = LoginHttpsMethods.post("login", requestLoginRoot);
		assertStatusCode(response.statusCode(), 200);

	}

	@Test
	public void ValidateOTP() {

		String payload = "{\r\n" + "    \"data\": {\r\n" + "        \"loginId\": \"7006523739\",\r\n"
				+ "        \"otp\":\"123456\",\r\n" + "         \"isdCode\":\"2345\",\r\n"
				+ "        \"deviceType\":\"\",\r\n" + "        \"osVersion\":\"17.2.1\",\r\n"
				+ "        \"deviceModel\" :\"OPPO F19 Pro+ 5G\",\r\n"
				+ "        \"firebaseToken\":\"cFQOL53_ikVvoeSiV8_0bm:APA91bEq6rhdQJydIjPLYWWp2qLXwM-fWsXXIFvxs9zJGTuloqw7bOvwqurYGnqrWRj_Y2cSgyJu3ieDgcyG2mf0gtSC_fb-7Lx1f3dYJQgmAZoJmbi9Q3GCa6YwcLUXVxoMBgnqv245\"\r\n"
				+ "        \r\n" + "    }\r\n" + "}";

		Response response = given().spec(SpecificationBuilder.GetRequestSpecHero()).body(payload).when()
				.post("verifyotp").then().spec(SpecificationBuilder.GetResponseSpecHero()).extract().response();

		assertStatusCode(response.statusCode(), 200);
		JsonPath jsonpath = response.jsonPath();
		String token = jsonpath.get("data.token").toString();
		System.out.println(token);

		byte[] barr = Base64.decodeBase64(token);
		System.out.println(barr);
		String str = new String(barr);
		System.out.println(str);

		String[] split_string = str.split("\\.");
		String base64EncodedHeader = split_string[0];
		String base64EncodedBody = split_string[1];

		System.out.println("~~~~~~~~~ JWT Header ~~~~~~~");
		Base64 base64Url = new Base64(true);
		String header = new String(base64Url.decode(base64EncodedHeader));
		System.out.println("JWT Header : " + header);

		System.out.println("~~~~~~~~~ JWT Body ~~~~~~~");
		String body = new String(base64Url.decode(base64EncodedBody));
		System.out.println("JWT Body : " + body);

		JSONObject json = new JSONObject(body);
		int expirationTime = json.getInt("exp");
		System.out.println(expirationTime);

	}

	@Test
	public void ValidateResponseHeader() {

		LoginBuilder("7006523739", "mobile");
		Response response = LoginHttpsMethods.post("login", requestLoginRoot);

		assertContentType(response.getContentType(), "application/json; charset=utf-8");
		System.out.println(response.getContentType());
		assertThat(response.header("Content-Type"), equalTo("application/json; charset=utf-8"));

	}

	@Test
	public void ValidateJsonSchema() {

		LoginBuilder("7006523739", "mobile");
		LoginHttpsMethods.post("login", requestLoginRoot, JsonSchema);

		// JsonSchema
		// Also import below package-
		// import static
		// io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
	}

	@Test
	public void ValidateResponseBody() {

		LoginPojo loginpojo = new LoginPojo("7006523739", "mobile"); // Creating object of LoginPojo class

		LoginPojoRoot loginpojoroot = new LoginPojoRoot(loginpojo); // Creating object of LoginPojoRoot class

		given().spec(SpecificationBuilder.GetRequestSpec()).body(loginpojoroot).when().post("login").then()
				.spec(SpecificationBuilder.GetResponseSpec()).assertThat().body("data.loginId", equalTo("7006523739"));

//		LoginPojoRoot deserializeloginpojoroot = given().spec(requestspec).body(loginpojoroot)
//
//				.when().post("login")
//
//				.then().spec(responsespec)
//
//				.extract().response().as(LoginPojoRoot.class);
//
//		assertThat(deserializeloginpojoroot.getMessage(), equalTo("OTP Generated Successfully"));
//		assertThat(deserializeloginpojoroot.getData().getLoginId(), equalTo(loginpojoroot.getData().getLoginId()));

	}

//	@Test
//	public void TimeCal() {
//		
//		
//		int expiryDurationInSeconds = 900;
//		Instant expiryTime = Instant.now().plusSeconds(expiryDurationInSeconds - 100);
//		System.out.println(expiryTime);
//
//		if (Instant.now().isAfter(expiryTime)) {
//			System.out.print("Expired Renewing Again !!!!!!!!!!!");
//		}
//
//		else {
//			System.out.print("Still going on !!!!!!!!!!!");
//		}
//
//	}

	public LoginPojoRoot LoginBuilder(String Login_ID, String Login_TYPE) {
//		HashMap<String, String> nestedJsonObj= new HashMap<String, String>();
//		nestedJsonObj.put("loginId", Login_ID);
//		nestedJsonObj.put("loginType", Login_TYPE);
//		
//		mainJsonObj= new HashMap<String, Object>();
//		mainJsonObj.put("data", nestedJsonObj);

		requestLogin = new LoginPojo();
		requestLogin.setLoginId(Login_ID);
		requestLogin.setLoginType(Login_TYPE);

		requestLoginRoot = new LoginPojoRoot(requestLogin);

		return requestLoginRoot;
	}

	public void assertStatusCode(int ActualStatusCode, int Expectedstatuscode) {
		assertThat(ActualStatusCode, equalTo(Expectedstatuscode));
	}

	public void assertContentType(String ActualContentType, String ExpectedContentType) {
		assertThat(ActualContentType, equalTo(ExpectedContentType));
	}

}
