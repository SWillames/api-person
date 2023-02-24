package com.sw.apiperson.personTestsRepository;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.sw.apiperson.dto.PersonDTO;
import com.sw.apiperson.model.Person;
import com.sw.apiperson.repository.PersonRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class PersonRepositoryTests {


	@Spy
	private ModelMapper mapper;


	@Autowired
	PersonRepository personRepository;

	@BeforeAll
	static void setUpFixture(){
		FixtureFactoryLoader.loadTemplates("com.sw.apiperson.loader");
	}

	@Test
	@DisplayName("Must successfully insert a person in database")
	void insertPerson() throws Exception {
		PersonDTO personDTO = Fixture.from(PersonDTO.class).gimme("valid");
		Person person = mapper.map(personDTO, Person.class);

		personRepository.save(person);
		Integer countPerson = personRepository.findAll().size();
		assertEquals(1, countPerson);
	}

	@Test
	@DisplayName("Checker person saved with birth date successfully")
	void checkPersonWithBirthDate(){
		PersonDTO personDTO = Fixture.from(PersonDTO.class).gimme("valid");
		Person person = mapper.map(personDTO, Person.class);
		personRepository.save(person);
		Integer countPerson = personRepository.findAll().size();
		Person person1 = personRepository.findByNameAndBirthDate(person.getName(), person.getBirthDate());

		assertNotNull(person1);
	}

	@Test
	@DisplayName("Checker person saved with birth date failure")
	void checkPersonWithBirthDateFailure() {
		PersonDTO personDTO = Fixture.from(PersonDTO.class).gimme("valid");
		Person person = mapper.map(personDTO, Person.class);

		personRepository.save(person);
		Integer countPerson = personRepository.findAll().size();
		Person person1 = personRepository.findByNameAndBirthDate("Person Test 3", LocalDate.of(1990, 12, 5));

		assertNull(person1);
	}





}
