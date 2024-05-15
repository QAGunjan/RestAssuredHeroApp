package com.restassuredheroapp.pojos;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;



@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter

public class Category {

	private Integer id;
	private String name;
	
	public Category()
	{
		
	}
	
	public Category(Integer id, String name)
	{
	setId(id);
	setName(name);
	}
}
