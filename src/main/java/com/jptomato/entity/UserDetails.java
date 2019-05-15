package com.jptomato.entity;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;

public class UserDetails extends User {

	@Getter
	private final com.jptomato.entity.User user;

	public UserDetails(com.jptomato.entity.User user, Collection<GrantedAuthority> authorities) {
		super(user.getUsername(), user.getPassword(), true, true, true, true, authorities);

		this.user = user;
	}
}
