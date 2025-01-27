package com.rodolfo.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rodolfo.business.converter.PersonConverter;
import com.rodolfo.business.dto.CellphoneDTO;
import com.rodolfo.business.dto.PersonDTO;
import com.rodolfo.infrastructure.entity.Cellphone;
import com.rodolfo.infrastructure.entity.Person;
import com.rodolfo.infrastructure.exceptions.ConflictExecption;
import com.rodolfo.infrastructure.exceptions.ResourceNotFoundException;
import com.rodolfo.infrastructure.repository.CellphoneRepository;
import com.rodolfo.infrastructure.repository.PersonRepository;
import com.rodolfo.infrastructure.security.JwtUtil;

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
	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private CellphoneRepository cellphoneRepository;

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
				throw new ConflictExecption("Email" + email + ", already registered");
			}
		} catch (ConflictExecption e) {
			throw new ConflictExecption("Email already registered", e.getCause());
		}
	}

	public boolean checkExistingEmail(String email) {
		return personRepository.existsByEmail(email);
	}

	public PersonDTO searchPersonByEmail(String email) {
		try {
			return personConverter.personEntityToPersonDTO(personRepository.findByEmail(email)
						.orElseThrow(() -> new ResourceNotFoundException(("Email" + email + " not found"))));
		} catch (ResourceNotFoundException e) {
			throw new ResourceNotFoundException("Email" + email + "not found");
		}
	}

	public void deletePersonByEmail(String email) {
		personRepository.deleteByEmail(email);
	}

	public PersonDTO updatePerson(String token, PersonDTO personDTO) {
		// Search email by token
		String email = jwtUtil.extractEmailByToken(token.substring(7));

		// Added encryption to the password
		personDTO.setPassword(personDTO.getPassword() != null ? passwordEncoder.encode(personDTO.getPassword()) : null);

		// Search person data on database
		Person person = personRepository.findByEmail(email)
					.orElseThrow(() -> new ResourceNotFoundException("Email" + email + "not found"));

		// Merge data receive in the DTO request with database data
		person = personConverter.personUpdate(personDTO, person);

		// Saved the converted person data, then took the return and converted it to personDTO
		return personConverter.personEntityToPersonDTO(personRepository.save(person));
	}

	public CellphoneDTO cellphoneUpdate(Long cellphoneId, CellphoneDTO cellphoneDTO) {
		Cellphone cellphoneEntity = cellphoneRepository.findById(cellphoneId)
					.orElseThrow(() -> new ResourceNotFoundException("Id" + cellphoneId + "not found"));
		Cellphone cellphone = personConverter.cellphoneUpdate(cellphoneDTO, cellphoneEntity);
		return personConverter.cellphoneEntityToCellphoneDTO(cellphoneRepository.save(cellphone));
	}

}
