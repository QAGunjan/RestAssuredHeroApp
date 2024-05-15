package com.restassuredheroapp.pojos;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)

@Getter
@Setter


public class LoginPojo {
	
	private String loginId;

	private String loginType;
	
	private boolean registeredUser;

	public LoginPojo()
	{
		
	}
	
	public LoginPojo(String loginId, String loginType)
	{
		this.loginId=loginId;
		this.loginType=loginType;
	}
	
	

	
	
	

}
