package com.rodolfo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rodolfo.business.PersonService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/person")
@RequiredArgsConstructor

public class PersonController {
	
	private final PersonService personService;
	
	
	
	
	
	

}
