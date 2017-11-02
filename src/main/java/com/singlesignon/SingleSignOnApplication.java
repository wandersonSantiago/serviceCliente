package com.singlesignon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class SingleSignOnApplication {

	 @RequestMapping(value = "/cliente")
	  public String available() {
	    return "Voce esta no microservice 1 do  cliente ";
	  }

	  @RequestMapping(value = "/checked-out")
	  public String checkedOut() {
	    return "Spring Boot in Action";
	  }
	public static void main(String[] args) {
		SpringApplication.run(SingleSignOnApplication.class, args);
	}
}
