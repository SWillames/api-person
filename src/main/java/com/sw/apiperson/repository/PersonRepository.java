package com.sw.apiperson.repository;

import com.sw.apiperson.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

	Person findByNameAndBirthDate(String s, LocalDate birthDate);
}
