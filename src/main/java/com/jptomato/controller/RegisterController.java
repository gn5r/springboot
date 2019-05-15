package com.jptomato.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.jptomato.entity.User;
import com.jptomato.repository.UserRepository;

@Controller
public class RegisterController {

	@Autowired
	private UserRepository reposirtoy;

	@Autowired
	MessageSource source;

	@ModelAttribute
	public User getUser() {
		return new User();
	}

	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("user", new User());
		return "register";
	}

	@PostMapping("/register")
	public String showForm(@Valid User user, BindingResult result, Model model) {

		if (result.hasErrors()) {
			return "register";
		}

		if (overlapCheck(user)) {
			model.addAttribute("error", true);
			return "register";
		} else {
			reposirtoy.insert(user);
			return "redirect:/";
		}
	}

	/*    登録するIDの重複チェック   *
	 *    既にあればtrueを返す       *
	 *                               */
	private boolean overlapCheck(User user) {
		List<User> list = reposirtoy.selectList();

		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getId() == user.getId()) {
				return true;
			}
		}
		return false;
	}
}
