package com.br.almoxarifado.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {
	
	@RequestMapping("/home")
	public ModelAndView home() {
		ModelAndView home = new ModelAndView();
		home.setViewName("home");
		return home;
	}
}
