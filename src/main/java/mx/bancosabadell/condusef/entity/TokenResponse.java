package mx.bancosabadell.condusef.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class TokenResponse {

	@JsonProperty
	private String msg;
	
	@JsonProperty
	private String message;

	@JsonProperty
	private UserTokenResponse user;
	
}
