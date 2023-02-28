package com.sw.apiperson.repository;

import com.sw.apiperson.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

	Person findByNameAndBirthDate(String name, LocalDate birthDate);
	Optional<Person> findByName(String name);

}
