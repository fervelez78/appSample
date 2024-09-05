package mx.bancosabadell.condusef.exceptions;

public class HttpResponseException extends Exception {
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 4358353637118249592L;
	
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