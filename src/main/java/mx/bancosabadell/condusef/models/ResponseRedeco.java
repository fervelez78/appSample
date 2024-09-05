package mx.bancosabadell.condusef.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import mx.bancosabadell.condusef.exceptions.ErrorInfoResponse;

@Data
public class ResponseRedeco {

	@JsonProperty("Número total de envios")
	private Integer numeroTotalDeEnvios;

	private List<Object> addedRows;
    
	private List<ErrorInfoResponse> errors;
    
	private List<InfoValidate> errorsValidate;
    
	private List<Queja> quejas;
    
	private String error;
	
	private String message;
    
	private List<String> errores;
	
	@JsonProperty("Quejas enviadas")
	private List<String> quejasEnviadas;
    
}
