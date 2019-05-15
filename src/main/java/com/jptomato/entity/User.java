package com.jptomato.entity;

import javax.validation.constraints.Min;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {

	@Min(0)
	private int id;

	private String username;
	private String password;
}
