package com.sw.apiperson.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "public_place", nullable = false)
	private String publicPlace;

	@Column(name = "zip_code", nullable = false)
	private String zipCode;

	@Column(nullable = true)
	private int number;

	@Column(nullable = false)
	private String city;

	@Column(name = "main_address")
	private boolean mainAddress;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "person_id")
	private Person person;
}
