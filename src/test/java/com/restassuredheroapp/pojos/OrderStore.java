package com.restassuredheroapp.pojos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.restassured.response.Response;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Getter
@Setter

public class OrderStore {

	private int id;
	private int petId;
	private int quantity;
	private String shipDate;
	private String status;
	private Boolean complete;

	public static OrderStore responseOrderStore;
	public static OrderStore requestOrderStore;

	public OrderStore() {

	}

//	public OrderStore(int id, int petId, int quantity, String shipDate, String status, Boolean complete) {
//
//		setId(id);
//		setPetId(petId);
//		setQuantity(quantity);
//		setShipDate(shipDate);
//		setStatus(status);
//		setComplete(complete);
//
//	}

	public static OrderStore OrderStoreBuilder(int id, int petId, int quantity, String shipDate, String status,
			Boolean complete) {

		 requestOrderStore = new OrderStore();

		requestOrderStore.setId(id);
		requestOrderStore.setPetId(petId);
		requestOrderStore.setQuantity(quantity);
		requestOrderStore.setShipDate(shipDate);
		requestOrderStore.setStatus(status);
		requestOrderStore.setComplete(complete);

		return requestOrderStore;

	}

	public static OrderStore SetUp(Response response) {
		responseOrderStore = response.as(OrderStore.class);
		responseOrderStore.setId(response.jsonPath().getInt("id"));
		responseOrderStore.setPetId(response.jsonPath().getInt("petId"));
		responseOrderStore.setQuantity(response.jsonPath().getInt("quantity"));

		return responseOrderStore;
	}

}
