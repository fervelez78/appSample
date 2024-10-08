package mx.bancosabadell.condusef.models;

import lombok.Data;

@Data
public class ResponseService {

    public ResponseService(String body, int code, String message) {
        this.body = body;
        this.code = code;
        this.message = message;
    }

    private String body;
    
    private Integer code;
    
    private String message;
}
