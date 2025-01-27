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
}
