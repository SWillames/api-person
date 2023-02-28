package com.sw.apiperson.service.person;

import com.sw.apiperson.dto.PersonDTO;
import com.sw.apiperson.exceptions.PersonNotFoundException;

import java.util.List;

public interface PersonServiceInterface {

	public PersonDTO createPerson(PersonDTO personDTO);

	public PersonDTO findByName(String name) throws PersonNotFoundException;

	public List<PersonDTO> listAll();

	public PersonDTO updatePersonDto(Long id, PersonDTO personDTO);

	void deletePersonById(Long id) throws PersonNotFoundException;


}
