package com.sw.apiperson.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonDTO {

	private Long id;

	@NotNull
	@Size(min = 1, max = 70)
	private String name;

	@NotNull
	private LocalDate birthDate;

	@Valid
	@NotEmpty
	private List<AddressDTO> adresses;


}
