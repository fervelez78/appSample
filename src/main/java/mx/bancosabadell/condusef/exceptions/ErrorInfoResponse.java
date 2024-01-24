package mx.bancosabadell.condusef.exceptions;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import mx.bancosabadell.condusef.models.QuejaInfoResponse;

@Data
public class ErrorInfoResponse {
    @JsonProperty("queja")
    private QuejaInfoResponse queja;
    @JsonProperty("errors")
    private List<String> errors;

    
}
