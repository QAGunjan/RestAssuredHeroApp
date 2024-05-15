
package com.restassuredheroapp.pojos;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)

@Getter
@Setter

public class BookStore {

	private String userId;
	private List<CollectionOfIsbn> collectionOfIsbns;

	private List<Book> books;
	
	public BookStore()
	{
		
	}

	public BookStore(String userId, List<CollectionOfIsbn> collectionOfIsbns) {
		setUserId(userId);
		setCollectionOfIsbns(collectionOfIsbns);
	}

	
	
	
}
