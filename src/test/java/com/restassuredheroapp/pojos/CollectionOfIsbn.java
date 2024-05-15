
package com.restassuredheroapp.pojos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)

@Getter
@Setter

public class CollectionOfIsbn {

	@JsonProperty("isbn")
	private String isbn;

	public CollectionOfIsbn() {
	}

	public CollectionOfIsbn(String isbn) {
		setIsbn(isbn);
	}

}
