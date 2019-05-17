package com.jptomato.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.jptomato.entity.User;
import com.jptomato.repository.UserRepository;

@Controller
public class UpdateController {

	@Autowired
	private UserRepository repository;

	private int oldId;

	@ModelAttribute
	public User getUser() {
		return new User();
	}

	@GetMapping("/update/id={id}")
	public String register(@PathVariable("id") int id, Model model) {
		/*    URL Paramより編集するユーザーIDから情報を取得    */
		User user = repository.findById(id);
		this.oldId = user.getId();
		model.addAttribute("user", user);
		return "update";
	}

	/*    入力後のボタン押下時のPost Request    */
	@PostMapping("/update/id={id}")
	public String showForm(@Valid User user, BindingResult result, Model model) {

		if (result.hasErrors()) {
			return "update";
		}

		if (overlapCheck(user)) {
			model.addAttribute("error", true);
			return "update";
		}
		repository.update(user.getId(), user.getUsername(), user.getPassword(), this.oldId);
		return "redirect:/";
	}

	/*    入力されたIDの重複チェック    */
	private boolean overlapCheck(User user) {
		List<User> list = repository.selectList();

		/*    IDを変更するかどうかの判断    */
		if (user.getId() == this.oldId) {
			/*    IDは変更しない場合    */
			return false;
		} else {
			/*    IDを変更する場合、重複してるかどうか    */
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getId() == user.getId()) {
					return true;
				}
			}
		}

		return false;
	}
}
