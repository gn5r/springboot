package com.jptomato.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.jptomato.entity.User;
import com.jptomato.repository.UserRepository;

@Controller
public class IndexController {

	@Autowired
	private UserRepository repository;

	@ModelAttribute
	public User getUser() {
		return new User();
	}

	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("list", repository.selectList());
		return "index";
	}

	@PostMapping("/")
	public String post(User user, Model model) {
		repository.insert(user);
		model.addAttribute("list", repository.selectList());
		return "redirect:/";
	}

	@GetMapping("/delete/{id}")
	public String deleteUser(User user) {
		repository.delete(user.getId());
		return "redirect:/";
	}
}
