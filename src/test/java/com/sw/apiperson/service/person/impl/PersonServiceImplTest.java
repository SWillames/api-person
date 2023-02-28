package com.sw.apiperson.service.person.impl;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.sw.apiperson.dto.PersonDTO;
import com.sw.apiperson.exceptions.PersonNotFoundException;
import com.sw.apiperson.model.Person;
import com.sw.apiperson.repository.PersonRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PersonServiceImplTest {
	private static final long INVALID_Person_ID = 1L;

	@Mock
	private PersonRepository personRepository;

	private PersonDTO personDTO;

	@Spy
	private ModelMapper mapper;

	@InjectMocks
	private PersonServiceImpl personService;

	@BeforeAll
	static void setUpFixture() {
		FixtureFactoryLoader.loadTemplates("com.sw.apiperson.loader");
	}

	@Test
	@DisplayName("It should be created")
	void itShouldBeCreated() {
		PersonDTO expectedPersonDTO = Fixture.from(PersonDTO.class).gimme("valid");
		Person expectedSavedPerson = mapper.map(expectedPersonDTO, Person.class);

		when(personRepository.save(expectedSavedPerson)).thenReturn(expectedSavedPerson);

		PersonDTO createdPersonDTO = personService.createPerson(expectedPersonDTO);

		assertThat(createdPersonDTO.getId(), is(equalTo(expectedPersonDTO.getId())));
		assertThat(createdPersonDTO.getName(), is(equalTo(expectedPersonDTO.getName())));
		assertThat(createdPersonDTO.getBirthDate(), is(equalTo(expectedPersonDTO.getBirthDate())));
		assertThat(createdPersonDTO.getAdresses(), is(equalTo(expectedPersonDTO.getAdresses())));
	}

	@Test
	@DisplayName("When valid person name is given then return a person")
	void getPersonByName() throws PersonNotFoundException {

		PersonDTO expectedFoundPersonDTO = Fixture.from(PersonDTO.class).gimme("valid");
		Person expectedFoundPerson = mapper.map(expectedFoundPersonDTO, Person.class);

		when(personRepository.findByName(expectedFoundPerson.getName())).thenReturn(Optional.of(expectedFoundPerson));

		PersonDTO foundPersonDTO = personService.findByName(expectedFoundPersonDTO.getName());

		assertThat(foundPersonDTO, is(equalTo(expectedFoundPersonDTO)));
	}

	@Test
	@DisplayName("When list person is called then return a list of persons")
	void listAllPersons() {

		PersonDTO expectedFoundPersonDTO = Fixture.from(PersonDTO.class).gimme("valid");
		Person expectedFoundPerson = mapper.map(expectedFoundPersonDTO, Person.class);

		when(personRepository.findAll()).thenReturn(Collections.singletonList(expectedFoundPerson));

		List<PersonDTO> foundListPersonsDTO = personService.listAll();

		assertThat(foundListPersonsDTO, is(not(empty())));
		assertThat(foundListPersonsDTO.get(0), is(equalTo(expectedFoundPersonDTO)));
	}

	@Test
	@DisplayName("When list person is called then return an empty list of persons")
	void getListEmpty() {

		when(personRepository.findAll()).thenReturn(Collections.EMPTY_LIST);

		List<PersonDTO> foundListPersonsDTO = personService.listAll();

		assertThat(foundListPersonsDTO, is(empty()));
	}

	@Test
	@DisplayName("When exclusion is called with valid id then a person should be deleted")
	void whenExclusionIsCalledWithValidIdThenAPersonShouldBeDeleted() throws PersonNotFoundException {

		PersonDTO expectedDeletedPersonDTO = Fixture.from(PersonDTO.class).gimme("valid");
		Person expectedDeletedPerson = mapper.map(expectedDeletedPersonDTO, Person.class);

		when(personRepository.findById(expectedDeletedPersonDTO.getId())).thenReturn(Optional.of(expectedDeletedPerson));
		doNothing().when(personRepository).deleteById(expectedDeletedPersonDTO.getId());

		personService.deletePersonById(expectedDeletedPersonDTO.getId()) ;

		verify(personRepository, times(1)).findById(expectedDeletedPersonDTO.getId());
		verify(personRepository, times(1)).deleteById(expectedDeletedPersonDTO.getId());
	}





}
