package com.example.demo.action;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthTestController {
	@RequestMapping("hello")
	@PreAuthorize("hasAuthority('authority_hello')")
	public String hello() {
		return "you call hello word controller.";
	}
}
