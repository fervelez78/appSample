package mx.bancosabadell.condusef.exceptions;

public class NetworkException extends Exception {

    private String detail;


    public NetworkException(String message) {
        super(message);
    }

    public NetworkException(String message, Throwable cause) {
        super(message, cause);
    }

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
