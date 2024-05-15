package com.restassuredheroapp.pojos;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class LoginPojoRoot {

	private LoginPojo data;
	private String message;
	private boolean success;

	public LoginPojoRoot() {

	}

	public LoginPojoRoot(LoginPojo data) {
		this.data = data;
	}

	public LoginPojo getData() {
		return data;
	}

	public void setData(LoginPojo data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	

}
