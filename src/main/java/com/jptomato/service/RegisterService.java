package com.jptomato.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jptomato.entity.User;
import com.jptomato.repository.UserRepository;

@Service
@Transactional
public class RegisterService {

	@Autowired
	private UserRepository mapper;

	public void RegisterUser(User user) {
		mapper.insert(user);
	}
}
