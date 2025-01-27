package com.rodolfo.business.converter;

import java.util.List;

import org.springframework.stereotype.Component;

import com.rodolfo.business.dto.CellphoneDTO;
import com.rodolfo.business.dto.PersonDTO;
import com.rodolfo.infrastructure.entity.Cellphone;
import com.rodolfo.infrastructure.entity.Person;

@Component
public class PersonConverter {
	
	public PersonDTO personEntityToPersonDTO(Person person) {
		return PersonDTO.builder()
				.name(person.getName())
				.email(person.getEmail())
				.password(person.getPassword())
				.cellphone(cellphoneListEntityToCellphoneListDTO(person.getCellphone()))
				.build();
	}
	public Person personDTOToPersonEntity(PersonDTO personDTO) {
		return Person.builder()
				.name(personDTO.getName())
				.email(personDTO.getEmail())
				.password(personDTO.getPassword())
				.cellphone(cellphoneListDTOToCellphoneListEntity(personDTO.getCellphone()))
				.build();
	}
	
	
	public List<CellphoneDTO> cellphoneListEntityToCellphoneListDTO(List<Cellphone> cellphone){
		return cellphone.stream().map(this::cellphoneEntityToCellphoneDTO).toList();
	}
	public List<Cellphone> cellphoneListDTOToCellphoneListEntity(List<CellphoneDTO> cellphoneDTO){
		return cellphoneDTO.stream().map(this::cellphoneDTOToCellphoneEntity).toList();
	}
	

	public CellphoneDTO cellphoneEntityToCellphoneDTO(Cellphone cellphone) {
		return CellphoneDTO.builder()
				.id(cellphone.getId())
				.areaCode(cellphone.getAreaCode())
				.number(cellphone.getNumber())
				.build();
	}
	public Cellphone cellphoneDTOToCellphoneEntity(CellphoneDTO cellphoneDTO) {
		return Cellphone.builder()
				.areaCode(cellphoneDTO.getAreaCode())
				.number(cellphoneDTO.getNumber())
				.build();
	}
	
	public Person personUpdate(PersonDTO personDTO, Person person) {
		return Person.builder()
				.id(person.getId())
				.name(personDTO.getName() != null ? personDTO.getName() : person.getName())
				.password(personDTO.getPassword() != null ? personDTO.getPassword() : person.getPassword())
				.email(personDTO.getEmail() != null ? personDTO.getEmail() : person.getEmail())
				.cellphone(person.getCellphone())
				.build();
	}
	
	public Cellphone cellphoneUpdate(CellphoneDTO cellphoneDTO, Cellphone cellphone) {
		return Cellphone.builder()
				.id(cellphone.getId())
				.areaCode(cellphoneDTO.getAreaCode() != null ? cellphoneDTO.getAreaCode() : cellphone.getAreaCode())
				.number(cellphoneDTO.getNumber() != null ? cellphoneDTO.getNumber() : cellphone.getNumber())
				.build();
	}
	
	
	
}
