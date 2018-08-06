package com.michaeldaviddunlap.colege_management_system.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	@RequestMapping("/")
	public String mainPage() {
		return "main-menu";
	}
}
