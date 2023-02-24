package com.sw.apiperson.personTestsRepository;

import com.sw.apiperson.model.Person;
import com.sw.apiperson.repository.PersonRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class PersonRepositoryTests {


	@Autowired
	PersonRepository personRepository;

	@Test
	@DisplayName("Must successfully insert a person in database")
	void insertPerson() throws Exception {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String date = "12/01/1988";
		LocalDate birthDate = LocalDate.parse(date, formatter);
		Person person = new Person("Person Test", birthDate);
		personRepository.save(person);
		Integer countPerson = personRepository.findAll().size();
		assertEquals(1, countPerson);
	}

	@Test
	@DisplayName("Checker person saved with birth date successfully")
	void checkPersonWithBirthDate(){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String date = "12/01/1989";
		LocalDate birthDate = LocalDate.parse(date, formatter);
		Person person = new Person("Person Test 2", birthDate);
		personRepository.save(person);
		Integer countPerson = personRepository.findAll().size();
		Person person1 = personRepository.findByNameAndBirthDate("Person Test 2", birthDate);

		assertNotNull(person1);
		assertEquals(person, person1);
	}

	@Test
	@DisplayName("Checker person saved with birth date failure")
	void checkPersonWithBirthDateFailure() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String date = "12/01/1989";
		LocalDate birthDate = LocalDate.parse(date, formatter);
		Person person = new Person("Person Test 3", birthDate);
		personRepository.save(person);
		Integer countPerson = personRepository.findAll().size();
		Person person1 = personRepository.findByNameAndBirthDate("Person Test 3", LocalDate.parse("14/05/1990", formatter));

		assertNull(person1);
	}





}
