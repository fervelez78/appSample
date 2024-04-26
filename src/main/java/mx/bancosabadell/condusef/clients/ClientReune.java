package mx.bancosabadell.condusef.clients;

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
import mx.bancosabadell.condusef.models.ConsultaData;
import mx.bancosabadell.condusef.models.ResponseRedeco;
import mx.bancosabadell.condusef.models.ResponseReune;
import mx.bancosabadell.condusef.models.ResponseService;
import mx.bancosabadell.condusef.services.CondusefBussines;
import mx.bancosabadell.condusef.services.CustomPropertyNamingStrategy;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class ClientReune extends ClientConducef{
    
    private static final Logger logger = LoggerFactory.getLogger("clientReuneLogger");
    
    private String tokenAccess = ConfigConstants.TOKEN_CONDUSEF;
    
    private CondusefBussines condusefBussines = new CondusefBussines();

    public ResponseReune postConsultasGeneral() throws Exception {
        logger.info("INICIO CARGA Reune " +  ConfigConstants.URL_API_REUNE);


        List<ConsultaData> listConsultas = condusefBussines.parseDocumentToBenXlsxReune();

        ResponseReune responseReune = condusefBussines.mapperDocumentConsulta(listConsultas);

        // Valida el response
        if (responseReune == null || responseReune.getConsultas() == null || 
        		responseReune.getConsultas().size() == 0)
        	throw new Exception("No se encontraron registros en el archivo");
        
         // Verificar si hay errores de validación
         if (responseReune.getErrorsValidate() == null && responseReune.getError() == null) {
            try {
                 // Serializar a JSON usando Jackson
                 ObjectMapper objectMapper = new ObjectMapper();
                 objectMapper.setPropertyNamingStrategy(new CustomPropertyNamingStrategy());
                 String requestConsultaTojson = objectMapper.writeValueAsString(responseReune.getConsultas());

                 
                logger.info("Request del cliente: " + requestConsultaTojson);

                // Crear el request que se mandará en la petición http
                RequestBody requestBody = RequestBody.create(requestConsultaTojson, MediaType.parse("application/json; charset=utf-8"));

                // Realizar la petición HTTP
                ResponseService response = post(pathReune, tokenAccess, requestBody);
                String responseToString = response.getBody();

                 // Parsear la respuesta a ResponseReune
                ObjectMapper objectMapperResponse = new ObjectMapper();
                
                responseReune = objectMapperResponse.readValue(responseToString, ResponseReune.class);
                if (responseReune.getError() != null) {
                    List<String> listErrors = new ArrayList<>();
                     for ( ErrorInfoResponse error : responseReune.getErrors()) {
                            listErrors.add(error.getConsulta().getErrors() + " : " + error.getConsulta().getConsultasFolio());

                            
                            logger.error(error.getConsulta().getErrors() + " : "+ error.getConsulta().getConsultasFolio());
                        }
                    
               }
               logger.info("Respuesta reune codigo estado " + response.getCode() + " - endpoint: " + ConfigConstants.URL_API_REUNE);

            } catch (JsonProcessingException e) {
                // Manejar errores de procesamiento JSON
            	logger.error("Error JSON processesing: " + e.getMessage());
            	handleJsonProcessingException(e, responseReune);
    
            } catch (HttpResponseException e) {
            	logger.error("Error HttpResponse: " + e.getMessage());
                // Manejar errores específicos de la respuesta HTTP
                handleHttpResponseException(e, responseReune);
    
            } catch (NetworkException e) {
            	logger.error("Error Network: " + e.getMessage());
                // Manejar errores de red
                handleNetworkException(e, responseReune);
    
            } catch (Exception e) {
            	logger.error("Error: " + e.getMessage());
                // Manejar cualquier otro error
                handleUnexpectedException(e, responseReune);
            }
        

        logger.info("REUNE " + ConfigConstants.URL_API_REUNE);
        logger.info("FIN CARGA Reune");
    }else{
        logger.info("Se obtivieron errores en el archivo");
    }

    return responseReune; 
    
}
private void handleJsonProcessingException(JsonProcessingException e, ResponseReune responseReune) {
        String errorMessage = "Error de procesamiento JSON: " + e.getMessage();
    	logger.error("Error al procesar el objeto a JSON: {}", e.getMessage(), e);
        responseReune.getConsultas().clear();
        responseReune.setError(errorMessage);
        if (responseReune.getErrores() == null)
        	responseReune.setErrores(new ArrayList<String>());
        responseReune.getErrores().add(errorMessage);
    }
    
    private void handleHttpResponseException(HttpResponseException e, ResponseReune responseReune) {
        String errorMessage = "Error HTTP: " + e.getStatusCode() + " - " + e.getMessage() + ":" + e.getResponseBody();
        logger.error(errorMessage);
        responseReune.getConsultas().clear();
        responseReune.setError(errorMessage);
        if (responseReune.getErrores() == null)
        	responseReune.setErrores(new ArrayList<String>());
        responseReune.getErrores().add(errorMessage);

    }
    
    private void handleNetworkException(NetworkException e, ResponseReune responseReune) {
        String errorMessage = "Error de red: " + e.getDetail() + " - " + e.getMessage();
        logger.error(errorMessage);
        responseReune.getConsultas().clear();
        responseReune.setError(errorMessage);
        if (responseReune.getErrores() == null)
        	responseReune.setErrores(new ArrayList<String>());
        responseReune.getErrores().add(errorMessage);
    }
    
    private void handleUnexpectedException(Exception e, ResponseReune responseReune) {
        String errorMessage = "Error inesperado: " + e;
        logger.error(errorMessage, e);
        responseReune.getConsultas().clear();
        responseReune.setError(errorMessage);
        if (responseReune.getErrores() == null)
        	responseReune.setErrores(new ArrayList<String>());
        responseReune.getErrores().add(errorMessage);
    }
}