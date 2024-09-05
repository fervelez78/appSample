package mx.bancosabadell.condusef.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class UserTokenResponse {

	@JsonProperty
	String token_access;
	
	@JsonProperty
	String username;
	
}
