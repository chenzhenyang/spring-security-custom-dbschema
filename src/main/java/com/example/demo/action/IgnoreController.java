package com.example.demo.action;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ignore")
public class IgnoreController {
	@RequestMapping("hello")
	public String hello() {
		return "you call ignore hello word controller.";
	}
}
