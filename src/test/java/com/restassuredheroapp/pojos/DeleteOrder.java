package com.restassuredheroapp.pojos;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.restassured.response.Response;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Getter
@Setter

public class DeleteOrder {
	
	private int code;
	private String type;
	private String message;
	
	public static DeleteOrder responseDeleteStore;
	
	
	public static DeleteOrder SetUp(Response response)
	{
		 responseDeleteStore = response.as(DeleteOrder.class);
		responseDeleteStore.setCode(response.jsonPath().getInt("code"));
		responseDeleteStore.setType(response.jsonPath().getString("type"));
		responseDeleteStore.setMessage(response.jsonPath().getString("message"));
		
         return responseDeleteStore;
	}

}
