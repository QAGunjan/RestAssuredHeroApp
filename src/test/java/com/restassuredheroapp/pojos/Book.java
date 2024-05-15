package com.restassuredheroapp.pojos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

@JsonInclude(Include.NON_NULL)
@Getter
@Setter
public class Book {

	private String isbn;
	private String title;
	private String subTitle;
	private String author;
	private String publish_date;
	private String publisher;
	private Integer pages;
	private String description;
	private String website;

}
