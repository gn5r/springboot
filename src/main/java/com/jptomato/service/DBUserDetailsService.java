package com.jptomato.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jptomato.entity.User;
import com.jptomato.repository.UserRepository;

@Service
public class DBUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository repository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = repository.findByName(username);
		if(user == null) {
			throw new UsernameNotFoundException("user not found");
		}

		return new com.jptomato.entity.UserDetails(user, getAuthorities(user));
	}

	private Collection<GrantedAuthority> getAuthorities(User user) {
		//認証が通った時にユーザに与える権限の範囲を設定する。
		return AuthorityUtils.createAuthorityList("ROLE_USER");
	}

}
