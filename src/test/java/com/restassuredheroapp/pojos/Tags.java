package com.restassuredheroapp.pojos;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter

public class Tags {

	private Integer id;
	private String name;

	public Tags() {

	}

	public Tags(Integer id, String name) {
		setId(id);
		setName(name);
	}

}
