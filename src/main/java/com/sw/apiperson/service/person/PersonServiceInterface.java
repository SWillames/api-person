package com.sw.apiperson.service.person;

import com.sw.apiperson.dto.PersonDTO;

import java.util.List;

public interface PersonServiceInterface {

	public PersonDTO createPerson(PersonDTO personDTO);

	public PersonDTO findByName(String name);

	public List<PersonDTO> listAll();

	public PersonDTO updatePersonDto(Long id, PersonDTO personDTO);

	void deletePersonById(Long id);


}
