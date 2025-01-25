package com.rodolfo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rodolfo.business.PersonService;
import com.rodolfo.business.dto.PersonDTO;
import com.rodolfo.infrastructure.entity.Person;
import com.rodolfo.infrastructure.security.JwtUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/person")
@RequiredArgsConstructor

public class PersonController {
	
	private final PersonService personService;
	private final AuthenticationManager authenticationManager;
	private final JwtUtil jwtUtil;
	
	@PostMapping
	public ResponseEntity<PersonDTO> personSave(@RequestBody PersonDTO personDTO) {
		return ResponseEntity.ok(personService.personSave(personDTO));
	}
	
	@PostMapping("/login")
	public String login(@RequestBody PersonDTO personDTO) {
		Authentication authentication = authenticationManager
			.authenticate(new UsernamePasswordAuthenticationToken(personDTO.getEmail(), personDTO.getPassword()));
		return "Bearer " + jwtUtil.generateToken(authentication.getName());

	}

	@GetMapping
	public ResponseEntity<Person> searchPersonByEmail(@RequestParam("email") String email) {
		return ResponseEntity.ok(personService.searchPersonByEmail(email));

	}

	@DeleteMapping("/{email}")
	public ResponseEntity<Void> deletePersonByEmail(@PathVariable String email) {
		personService.deletePersonByEmail(email);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping
	public ResponseEntity<PersonDTO> updatePersonData(@RequestBody PersonDTO personDTO, @RequestHeader("Authorization") String token){
		return ResponseEntity.ok(personService.updateDataPerson(token, personDTO));
	}
	
	
	

}
