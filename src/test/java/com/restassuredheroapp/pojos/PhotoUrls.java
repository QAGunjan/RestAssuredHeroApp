package com.restassuredheroapp.pojos;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter

public class PhotoUrls {

	private String string;

	public PhotoUrls() {
	}

	public PhotoUrls(String string) {
		setString(string);
	}
}
