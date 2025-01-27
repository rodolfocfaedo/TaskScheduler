package com.rodolfo.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rodolfo.business.converter.PersonConverter;
import com.rodolfo.business.dto.PersonDTO;
import com.rodolfo.infrastructure.entity.Person;
import com.rodolfo.infrastructure.exceptions.ConflictExecption;
import com.rodolfo.infrastructure.repository.PersonRepository;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class PersonService {
	
	@Autowired
	private PersonRepository personRepository;
	@Autowired
	private PersonConverter personConverter;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public PersonDTO personSave(PersonDTO personDTO) {
		try {
			emailExists(personDTO.getEmail());
			personDTO.setPassword(passwordEncoder.encode(personDTO.getPassword()));
			Person person = personConverter.personDTOToPersonEntity(personDTO);
			return personConverter.personEntityToPersonDTO(personRepository.save(person)); 
		} catch (ConflictExecption e) {
			throw new ConflictExecption("Email already registered");
		}
	}
	
	public void emailExists(String email) {
		try {
			boolean exists = checkExistingEmail(email);
			if (exists) {
				throw new ConflictExecption("Email" + email + "alrey registered");
			}
		} catch (ConflictExecption e) {
			throw new ConflictExecption("Email already registered", e.getCause());
		}
	}
	
	public boolean checkExistingEmail(String email) {
		return personRepository.existsByEmail(email);
	}
	
	
	

}
