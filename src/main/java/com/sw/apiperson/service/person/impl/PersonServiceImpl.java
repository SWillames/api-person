package com.sw.apiperson.service.person.impl;

import com.sw.apiperson.dto.PersonDTO;
import com.sw.apiperson.model.Person;
import com.sw.apiperson.repository.PersonRepository;
import com.sw.apiperson.service.person.PersonServiceInterface;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonServiceImpl implements PersonServiceInterface {

	private final PersonRepository personRepository;
	private final ModelMapper mapper;

	@Override
	public PersonDTO createPerson(PersonDTO personDTO) {
		Person person = mapper.map(personDTO, Person.class);
		Person savedPerson = personRepository.save(person);
		return mapper.map(savedPerson, PersonDTO.class);
	}

	@Override
	public PersonDTO findByName(String name) {
		Person foundPerson = personRepository.findByName(name);
		return mapper.map(foundPerson, PersonDTO.class);
	}

	@Override
	public List<PersonDTO> listAll() {
		List<Person> people = personRepository.findAll();
		return people.stream()
					  .map(person -> mapper.map(person, PersonDTO.class))
					  .collect(Collectors.toList());
	}

	@Override
	public PersonDTO updatePersonDto(Long id, PersonDTO personDTO) {
		return null;
	}

	@Override
	public void deletePersonById(Long id) {
		personRepository.findById(id);
		personRepository.deleteById(id);
	}





}
