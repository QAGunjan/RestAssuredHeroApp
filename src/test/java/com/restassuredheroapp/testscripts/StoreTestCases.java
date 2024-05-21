package com.restassuredheroapp.testscripts;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.restassuredheroapp.pojos.DeleteOrder;
import com.restassuredheroapp.pojos.OrderStore;
import com.restassuredheroapp.utils.FakerData;
import com.restassuredheroapp.utils.SpecificationBuilder;

import io.restassured.response.Response;

public class StoreTestCases {

	@Test
	public void CreateOrder() {

//		OrderStore serialized = new OrderStore(FakerData.GetRandomNumber(), 112,113, "2023-12-22T12:08:21.618Z", "placed", true);

		OrderStore serialized = OrderStore.OrderStoreBuilder(FakerData.GetRandomNumber(), FakerData.GetRandomNumber(),FakerData.GetRandomNumber(), "2023-12-22T12:08:21.618Z", "placed", true);
		
		Response response = given().spec(SpecificationBuilder.GetRequestSpec()).queryParam("api_key", "special-key")
				.body(serialized).when().post("/store/order").then().spec(SpecificationBuilder.GetResponseSpec())
				.extract().response();
		
		OrderStore.SetUp(response);

		Assert.assertEquals(response.statusCode(), 200);
		Assert.assertTrue(response.statusLine().contains("OK"));
		Assert.assertTrue(response.getHeader("Content-Type").equals("application/json"));
		Assert.assertEquals(OrderStore.responseOrderStore.getId(), OrderStore.requestOrderStore.getId());
		Assert.assertEquals(OrderStore.responseOrderStore.getPetId(), OrderStore.requestOrderStore.getPetId());
		Assert.assertEquals(OrderStore.responseOrderStore.getQuantity(), OrderStore.requestOrderStore.getQuantity());
		

	}

	@Test(dependsOnMethods = "CreateOrder")
	public void GetOrder() {

	//	OrderStore serialized = new OrderStore(14859, 0, 0, "2023-12-22T12:08:21.618+0000", "placed", true);

		Response response = given().spec(SpecificationBuilder.GetRequestSpec()).queryParam("api_key", "special-key")
	//			.body(serialized)
				.when().get("/store/order" + "/" + OrderStore.responseOrderStore.getId())
				.then().spec(SpecificationBuilder.GetResponseSpec())
				.extract().response();

		Assert.assertEquals(response.statusCode(), 200);
		Assert.assertTrue(response.statusLine().contains("OK"));
		Assert.assertTrue(response.getHeader("Content-Type").equals("application/json"));
		Assert.assertEquals(OrderStore.responseOrderStore.getId(), OrderStore.requestOrderStore.getId());

	}
	
	@Test(dependsOnMethods = "GetOrder")
	public void DeleteOrder() {

//		OrderStore serialized = new OrderStore(14859, 0, 0, "2023-12-22T12:08:21.618+0000", "placed", true);

		Response response = given().spec(SpecificationBuilder.GetRequestSpec()).queryParam("api_key", "special-key")
				.when().delete("/store/order" + "/" + OrderStore.responseOrderStore.getId()).then().spec(SpecificationBuilder.GetResponseSpec())
				.extract().response();

		DeleteOrder.SetUp(response);
		
		Assert.assertEquals(response.statusCode(), 200);
		Assert.assertTrue(response.statusLine().contains("OK"));
		Assert.assertTrue(response.getHeader("Content-Type").equals("application/json"));
		Assert.assertEquals(DeleteOrder.responseDeleteStore.getCode(), 200);
		Assert.assertEquals(DeleteOrder.responseDeleteStore.getType(), "unknown");
		int orderID = OrderStore.responseOrderStore.getId();
		String orderIDAsString = Integer.toString(orderID);
		Assert.assertEquals(DeleteOrder.responseDeleteStore.getMessage(), orderIDAsString);

	}


}
