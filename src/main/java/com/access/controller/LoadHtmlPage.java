package com.access.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoadHtmlPage {

	@RequestMapping("/")
	public String getHomePage() {
		return "ajax2";
	}
}
