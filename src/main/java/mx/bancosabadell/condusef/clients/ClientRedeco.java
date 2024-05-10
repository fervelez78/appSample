package mx.bancosabadell.condusef.clients;


import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import mx.bancosabadell.condusef.config.ConfigConstants;
import mx.bancosabadell.condusef.exceptions.ErrorInfoResponse;
import mx.bancosabadell.condusef.exceptions.HttpResponseException;
import mx.bancosabadell.condusef.exceptions.NetworkException;
import mx.bancosabadell.condusef.models.QuejasData;
import mx.bancosabadell.condusef.models.ResponseRedeco;
import mx.bancosabadell.condusef.models.ResponseService;
import mx.bancosabadell.condusef.services.CondusefBussines;
import mx.bancosabadell.condusef.services.CustomPropertyNamingStrategy;
import mx.bancosabadell.condusef.services.LatiniaMailService;
import mx.bancosabadell.condusef.services.MailService;
import okhttp3.MediaType;
import okhttp3.RequestBody;


public class ClientRedeco extends ClientConducef{

    private static final Logger logger = LoggerFactory.getLogger("clientRedecoLogger");

    private String tokenAccess = ConfigConstants.TOKEN_CONDUSEF;

    //Logica de negocio
    private CondusefBussines condusefBussines = new CondusefBussines();
    
    public String getQuejas() {
        logger.info("Inicio request redeco al endpoint: " + pathRedeco);    
        String response = new String();
        String error = "";
        try {
            response = get(pathRedeco, tokenAccess);
            logger.info("Response redeco endpoin: " + ConfigConstants.URL_API_REDECO + "n/"+ response);
        } catch (HttpResponseException e) {
            // Manejar errores específicos de la respuesta HTTP
            error = "Error HTTP: " + e.getStatusCode() + " - " + e.getMessage() + ":" +e.getResponseBody();
            logger.error(error);
        } catch (NetworkException e) {
            // Manejar errores de red
            error = "Error de red: " + e.getDetail() +" - ";
            logger.error(error);
        } catch (Exception e) {
            // Manejar cualquier otro error
            error = "Error inesperado: " + e.getMessage();
            logger.error(error);
        }
    	/* if (!error.equals("")) {
    		logger.info("Enviando correo a " + ConfigConstants.CORREO_NOTIF_REDECO);
	        MailService mail = new LatiniaMailService(); 
	    	mail.send(ConfigConstants.CORREO_NOTIF_REDECO, "Proceso CONDUSEF: Error en REDECO", error);
    	} */
        logger.info("FIN CONSULTA REDECO");
        return response;
    }

    
    public ResponseRedeco postQuejas() throws Exception{
        logger.info("Inicio request redeco al endpoint: " + ConfigConstants.URL_API_REDECO);
        ResponseRedeco responseRedeco = null;
        // Se obtiene una lista de quejas cvs desde un documento cvs o txt
        List<QuejasData> listQuejasData = condusefBussines.parseDocumentToBenXlsx();
    
        // Se obtiene un ResponseRedeco con lista de quejas que se mapean desde la lista de quejas cvs
        responseRedeco = condusefBussines.mapperDocumentQueja(listQuejasData);
        
        // Valida el response
        if (responseRedeco == null || responseRedeco.getQuejas() == null || 
        		responseRedeco.getQuejas().size() == 0)
        	throw new Exception("No se encontraron registros en el archivo");
        
        // Verificar si hay errores de validación
        if (responseRedeco.getErrorsValidate() == null && responseRedeco.getError() == null) {
            try {
                // Serializar a JSON usando Jackson
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.setPropertyNamingStrategy(new CustomPropertyNamingStrategy());
                String requestQuejaTojson = objectMapper.writeValueAsString(/* quejas */responseRedeco.getQuejas());
    
                logger.info("Request del cliente: " + requestQuejaTojson);
    
                // Crear el request que se mandará en la petición http
                RequestBody requestBody = RequestBody.create(requestQuejaTojson, MediaType.parse("application/json; charset=utf-8"));
    
                // Realizar la petición HTTP
                ResponseService response = post(pathRedeco, tokenAccess, requestBody);
                String responseToString = response.getBody();
                // Parsear la respuesta a ResponseRedeco
                ObjectMapper objectMapperResponse = new ObjectMapper();
                responseRedeco = objectMapperResponse.readValue(responseToString, ResponseRedeco.class);
                if (responseRedeco.getErrors().size() != 0) {
                    List<String> listErrors = new ArrayList<>();
                     for ( ErrorInfoResponse error : responseRedeco.getErrors()) {
                            listErrors.add(error.getQueja().getErrors() + " : " + error.getQueja().getQuejasFolio());

                          /*   System.out.println(error.getQueja().getErrors() + " : " + error.getQueja().getQuejasFolio()); */
                            logger.error(error.getQueja().getErrors() + " : "+ error.getQueja().getQuejasFolio());
                        }
                    
               }
    
                logger.info("Respuesta redeco codigo estado " + response.getCode() + " - endpoint: " + ConfigConstants.URL_API_REDECO);
    
            } catch (JsonProcessingException e) {
                // Manejar errores de procesamiento JSON
            	logger.error("Error JSON processesing: " + e.getMessage());
            	handleJsonProcessingException(e, responseRedeco);
    
            } catch (HttpResponseException e) {
            	logger.error("Error HttpResponse: " + e.getMessage());
                // Manejar errores específicos de la respuesta HTTP
                handleHttpResponseException(e, responseRedeco);
    
            } catch (NetworkException e) {
            	logger.error("Error Network: " + e.getMessage());
                // Manejar errores de red
                handleNetworkException(e, responseRedeco);
    
            } catch (Exception e) {
            	logger.error("Error: " + e.getMessage());
                // Manejar cualquier otro error
                handleUnexpectedException(e, responseRedeco);
            }
            
            logger.info("FIN CONSULTA REDECO");
        }
    
        return responseRedeco;

    }

    private void handleJsonProcessingException(JsonProcessingException e, ResponseRedeco responseRedeco) {
        String errorMessage = "Error de procesamiento JSON: " + e.getMessage();
    	logger.error("Error al procesar el objeto a JSON: {}", e.getMessage(), e);
        responseRedeco.getQuejas().clear();
        responseRedeco.setError(errorMessage);
        if (responseRedeco.getErrores() == null)
        	responseRedeco.setErrores(new ArrayList<String>());
        responseRedeco.getErrores().add(errorMessage);
    }
    
    private void handleHttpResponseException(HttpResponseException e, ResponseRedeco responseRedeco) {
        String errorMessage = "Error HTTP: " + e.getStatusCode() + " - " + e.getMessage() + ":" + e.getResponseBody();
        logger.error(errorMessage);
        responseRedeco.getQuejas().clear();
        responseRedeco.setError(errorMessage);
        if (responseRedeco.getErrores() == null)
        	responseRedeco.setErrores(new ArrayList<String>());
        responseRedeco.getErrores().add(errorMessage);
    }
    
    private void handleNetworkException(NetworkException e, ResponseRedeco responseRedeco) {
        String errorMessage = "Error de red: " + e.getDetail() + " - " + e.getMessage();
        logger.error(errorMessage);
        responseRedeco.getQuejas().clear();
        responseRedeco.setError(errorMessage);
        if (responseRedeco.getErrores() == null)
        	responseRedeco.setErrores(new ArrayList<String>());
        responseRedeco.getErrores().add(errorMessage);
    }
    
    private void handleUnexpectedException(Exception e, ResponseRedeco responseRedeco) {
        String errorMessage = "Error inesperado: " + e;
        logger.error(errorMessage, e);
        responseRedeco.getQuejas().clear();
        responseRedeco.setError(errorMessage);
        if (responseRedeco.getErrores() == null)
        	responseRedeco.setErrores(new ArrayList<String>());
        responseRedeco.getErrores().add(errorMessage);
    }
    
}
