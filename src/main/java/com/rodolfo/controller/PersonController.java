package com.rodolfo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rodolfo.business.PersonService;
import com.rodolfo.business.dto.PersonDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/person")
@RequiredArgsConstructor

public class PersonController {
	
	private final PersonService personService;
	
	@PostMapping
	public ResponseEntity<PersonDTO> personSave(@RequestBody PersonDTO personDTO) {
		return ResponseEntity.ok(personService.personSave(personDTO));
	}
	
	
	
	

}
