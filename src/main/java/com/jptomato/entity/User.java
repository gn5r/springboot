package com.jptomato.entity;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {

	@Min(0)
	private int id;

	@Size(min = 1, max = 16)
	@Pattern(regexp = ".*\\S+.*")
	private String username;
	@Pattern(regexp = ".*\\S+.*")
	private String password;
}
