package com.sw.apiperson.loader;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.sw.apiperson.dto.AddressDTO;
import com.sw.apiperson.dto.PersonDTO;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import static com.sw.apiperson.FixtureHelper.MIN_RANGE_ID;
import static com.sw.apiperson.FixtureHelper.MAX_RANGE_ID;
import static com.sw.apiperson.FixtureHelper.MAX_RANGE_NUMBER;
import static com.sw.apiperson.FixtureHelper.MIN_RANGE_NUMBER;


public class PersonLoader implements TemplateLoader {

	@Override
	public void load(){

		Fixture.of(PersonDTO.class).addTemplate("valid", new Rule(){
			{
				add("id", random(Long.class, range(MIN_RANGE_ID, MAX_RANGE_ID)));
				add("name", random("John Wick", "John McClane", "Homer Simpson", "Charlie Harper", "Chuck Norris"));
				add("birthDate", LocalDate.of(2010, 10, 9));
				add("adresses", has(2).of(AddressDTO.class, "valid"));
			}
		});

		Fixture.of(AddressDTO.class).addTemplate("valid", new Rule(){
			{
				add("id", random(Long.class, range(MIN_RANGE_ID, MAX_RANGE_ID)));
				add("publicPlace", random("Bucar Neto avenue", "Padre Uchoa Street",  "Getulio Vargas avenue"));
				add("number", random(Integer.class, range(MIN_RANGE_NUMBER, MAX_RANGE_NUMBER)));
				add("city", random("Teresina", "Floriano", "Agua Branca"));
				add("zipCode", random("64000000", "64900000"));
				add("mainAddress", random(true, false));
			}
		});


	}
}
