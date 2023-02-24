package com.sw.apiperson.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {

	private Long id;

	@NotNull
	private String publicPlace;

	@NotNull
	private String zipCode;


	private int number;

	@NotNull
	private String city;

	@NotEmpty
	private boolean mainAddress;

	@NotNull
	private Long personId;
}
