package mx.bancosabadell.condusef.exceptions;

public class NetworkException extends Exception {

    private String detail;



    // Constructor que acepta solo un mensaje
    public NetworkException(String message) {
        super(message);
    }

    // Constructor que acepta un mensaje y una causa (otra excepción)
    public NetworkException(String message, Throwable cause) {
        super(message, cause);
    }
     // Constructor que acepta un mensaje y una causa (otra excepción) con descripción
    public NetworkException(String message, Throwable cause, String detail) {
        super(message, cause);
        this.detail = detail;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
    

}
