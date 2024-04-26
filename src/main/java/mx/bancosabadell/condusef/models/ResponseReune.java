package mx.bancosabadell.condusef.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import mx.bancosabadell.condusef.exceptions.ErrorInfoResponse;

@Data
public class ResponseReune {
        
        private List<ErrorInfoResponse> errors;
        
        private List<InfoValidate> errorsValidate;

        @JsonProperty("Consultas enviadas")
        private List<String> consultasEnviadas;
        
        private List<Consulta> consultas; 

        @JsonProperty("Número total de envios")
        private int numeroTotalDeEnvios;
        
        private String error;
        
        private List<String> errores;
        
    }
    
