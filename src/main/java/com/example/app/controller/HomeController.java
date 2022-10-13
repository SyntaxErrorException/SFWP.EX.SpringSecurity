package com.example.app.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.app.domain.User;

@Controller
public class HomeController {
	@GetMapping({"/", "/home"})
	public String home(@AuthenticationPrincipal User user) {
		System.out.println(user);
		return "home";
	}
}
