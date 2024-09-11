package mx.bancosabadell.condusef.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import mx.bancosabadell.condusef.exceptions.ErrorInfoResponse;

@Data
public class ResponseReuneAclaraciones {

	private List<ErrorInfoResponse> errors;
    
    private List<InfoValidate> errorsValidate;
    
    @JsonProperty("Aclaraciones enviadas")
    private List<String> aclaracionesEnviadas;
    
    private List<Aclaracion> aclaraciones; 

    @JsonProperty("Número total de envios")
    private int numeroTotalDeEnvios;
    
    private String message;

    private String error;
    
    private List<String> errores;
	
}
