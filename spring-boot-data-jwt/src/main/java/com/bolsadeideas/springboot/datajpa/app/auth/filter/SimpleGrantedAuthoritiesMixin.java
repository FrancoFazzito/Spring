package com.bolsadeideas.springboot.datajpa.app.auth.filter;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class SimpleGrantedAuthoritiesMixin {

	@JsonCreator
	public SimpleGrantedAuthoritiesMixin(@JsonProperty("Authorities") String roles) {
		
	}
}
