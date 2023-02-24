package com.sw.apiperson.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(name = "birth_date", nullable = false)
	private LocalDate birthDate;

	@OneToMany(mappedBy = "person", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private List<Address> adresses;

	public Person(String name, LocalDate birthDate) {
		this.name = name;
		this.birthDate = birthDate;
		this.adresses = new ArrayList<>();
	}

	public Person() {
		super();
		this.adresses = new ArrayList<Address>();
	}

	public Person(String name) {
		this.name = name;
	}

	public void addAdresses(Address address) {
		address.setPerson(this);
		this.adresses.add(address);
	}
}
