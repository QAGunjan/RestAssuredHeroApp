package com.restassuredheroapp.pojos;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter

public class Error {

	private Integer code;
	private String type;
	private String message;

	public Error() {

	}

	public Error(Integer code, String type, String message) {
		setCode(code);
		setType(type);
		setMessage(message);
	}

}
