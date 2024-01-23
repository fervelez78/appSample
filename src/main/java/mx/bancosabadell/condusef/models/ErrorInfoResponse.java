package mx.bancosabadell.condusef.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ErrorInfoResponse {
    @JsonProperty("queja")
    private QuejaInfoResponse queja;
    @JsonProperty("errors")
    private List<String> errors;

    
}
