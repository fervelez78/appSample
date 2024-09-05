package mx.bancosabadell.condusef.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class UserRequest {

	@JsonProperty
	String username;
	
	@JsonProperty
	String password;
	
	public UserRequest(String username, String Password) {
		
		setUsername(username);
		setPassword(Password);
		
	}
}
