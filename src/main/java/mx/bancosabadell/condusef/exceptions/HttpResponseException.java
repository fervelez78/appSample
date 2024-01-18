package mx.bancosabadell.condusef.exceptions;

public class HttpResponseException extends Exception {
    private final int statusCode;
    private final String responseBody;

    
    public HttpResponseException(int statusCode, String message, String responseBody) {
        super(message);
        this.statusCode = statusCode;
        this.responseBody = responseBody;
    }
    
    public String getResponseBody() {
        return responseBody;
    }
    public int getStatusCode() {
        return statusCode;
    }
}