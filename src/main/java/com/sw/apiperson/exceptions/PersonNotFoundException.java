package com.sw.apiperson.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PersonNotFoundException extends Exception{
	public PersonNotFoundException(String personName) {
		super(String.format("Person %s not found in the system.", personName));
	}

	public PersonNotFoundException(Long id) {
		super(String.format("Person with id %s not found in the system.", id));
	}
}
