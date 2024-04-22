package mx.bancosabadell.condusef.models;

import java.util.List;

import lombok.Data;
import mx.bancosabadell.condusef.exceptions.ErrorInfoResponse;

@Data
public class ResponseReune {
        
        private List<ErrorInfoResponse> errors;
        
        private List<InfoValidate> errorsValidate;
        
        private List<Consulta> consultas; 
        
        private String error;
        
        private List<String> errores;
        
    }
    
