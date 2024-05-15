package com.restassuredheroapp.pojos;

import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Getter
@Setter

public class PetStore {

	private Long id;
	private Category category;
	private String name;
	private List<String> photoUrls;
	private List<Tags> tags;
	private String status;

	public PetStore() {

	}

	public PetStore(Long i, Category category, String name, List<String> photoUrls, List<Tags> tags, String status) {
		setId(i);
		setCategory(category);
		setName(name);
		setPhotoUrls(photoUrls);
		setTags(tags);
		setStatus(status);
	}

	public PetStore GetPayload(String name) {
		Tags tags = new Tags(0, "string");

		List<Tags> tagslinkedlist = new LinkedList<Tags>();
		tagslinkedlist.add(tags);

		List<String> photoURLlinkedlist = new LinkedList<String>();
		photoURLlinkedlist.add("string");

		Category category = new Category(0, "string");

		PetStore serialized = new PetStore((long) 0, category, name, photoURLlinkedlist, tagslinkedlist, "available");

		return serialized;
	}

}
