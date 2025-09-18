package com.main.cotroller;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class MainController {

	@GetMapping("/")
	public String main() {
		return "index";
	}
}
