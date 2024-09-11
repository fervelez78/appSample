package mx.bancosabadell.condusef.clients;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import mx.bancosabadell.condusef.config.ConfigConstants;
import mx.bancosabadell.condusef.entity.TokenResponse;
import mx.bancosabadell.condusef.entity.UserRequest;
import mx.bancosabadell.condusef.exceptions.ErrorInfoResponse;
import mx.bancosabadell.condusef.exceptions.HttpResponseException;
import mx.bancosabadell.condusef.exceptions.NetworkException;
import mx.bancosabadell.condusef.models.ConsultaData;
import mx.bancosabadell.condusef.models.ResponseReune;
import mx.bancosabadell.condusef.models.ResponseReuneAclaraciones;
import mx.bancosabadell.condusef.models.ResponseService;
import mx.bancosabadell.condusef.services.CondusefBussines;
import mx.bancosabadell.condusef.services.CustomPropertyNamingStrategy;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Objeto para el uso de la API de REUNE.
 */
public class ClientReune extends ClientConducef{
    
	/**
     * Logger de REUNE. 
     */
    private static final Logger logger = LoggerFactory.getLogger("clientReuneLogger");
    
    /**
     * Objeto de negocio de CONDUSEF.
     */
    private CondusefBussines condusefBussines;

    /**
     * Método para obtener el token del servicio de REUNE.
     * @return Token recuperado.
     * @throws Exception Error generado al consumir el servicio de token de REUNE.
     */
    public String getToken() throws Exception {

    	logger.info("Entrnado a getToken");
    	
		UserRequest user = null;
		ObjectMapper mapper = new ObjectMapper();
    	if (getRazonSocial().trim().toUpperCase().equals("SABADELL")) {
    		user = new UserRequest(ConfigConstants.getUserNameReuneSabadell(), ConfigConstants.getPasswordReuneSabadell());
    	}
        
    	if (getRazonSocial().trim().toUpperCase().equals("SOFOM")) {
    		user = new UserRequest(ConfigConstants.getUserNameReuneSofom(), ConfigConstants.getPasswordReuneSofom());
    	}

    	        
		String json = mapper.writeValueAsString(user);

		logger.info("JSON: {}", json);
		logger.info("URL Token: {}", ConfigConstants.getUrlTokenReune());
		logger.info("PATH Token: {}", ConfigConstants.getPathTokenReune());
        // Realizar la petición HTTP
        String response = get(ConfigConstants.getUrlTokenReune(), ConfigConstants.getPathTokenReune(), json);
		TokenResponse token = mapper.readValue(response, TokenResponse.class);
		logger.info("Mensaje: " + token.getMsg());
		logger.info("Token: " + token.getUser().getToken_access());
		return token.getUser().getToken_access();		
    }

    /**
     * Método para el consumo del servicio de REUNE para el envío de consultas.
     * @return Respuesta de REUNE.
     * @throws Exception Error generado al consumir el servicio.
     */
    public ResponseReune postConsultasGeneral(String razonSocial) throws Exception {
        logger.info("INICIO CARGA Reune " + pathReune);

        setRazonSocial(razonSocial);
        condusefBussines = new CondusefBussines(razonSocial);

        List<ConsultaData> listConsultas = condusefBussines.parseDocumentToBenXlsxReune();

        ResponseReune responseReune = condusefBussines.mapperDocumentConsulta(listConsultas);

        // Valida el response
        if (responseReune == null || responseReune.getConsultas() == null || 
        		responseReune.getConsultas().size() == 0)
        	//throw new Exception("No se encontraron registros en el archivo");
        	responseReune.setError("No se encontraron registros en el archivo");
        
         // Verificar si hay errores de validación
         if (responseReune.getErrorsValidate() == null && responseReune.getError() == null) {
            try {
                // Serializar a JSON usando Jackson
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.setPropertyNamingStrategy(new CustomPropertyNamingStrategy());
                String requestConsultaTojson = objectMapper.writeValueAsString(responseReune.getConsultas());
                
                // Obtenemos un nuevo token
            	logger.info("Antes de obtener el token" );
                String tokenAccess = getToken();
                logger.info("Despues de obtener el token: {}", tokenAccess);

                logger.info("Request del cliente: {}", requestConsultaTojson);

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
               logger.info("Respuesta reune codigo estado " + response.getCode() + " - endpoint: " + ConfigConstants.getUrlApiReune());
               if (response.getCode() == 200)
            	   logger.info("Mensaje: " + response.getMessage());

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
        
	        logger.info("REUNE " + ConfigConstants.getUrlApiReune());
	        logger.info("FIN CARGA Reune");
	    }else{
	        logger.info("Se obtivieron errores en el archivo");
	    }
	
	    return responseReune; 
	    
    }
    
    /**
     * Método para dispersar el error en el comsumo del servicio de REUNE.
     * @param e Excepción generada.
     * @param responseReune Respuesta de REUNE. 
     */
    private void handleJsonProcessingException(JsonProcessingException e, ResponseReune responseReune) {
        String errorMessage = "Error de procesamiento JSON: " + e.getMessage();
    	logger.error("Error al procesar el objeto a JSON: {}", e.getMessage(), e);
        responseReune.getConsultas().clear();
        responseReune.setError(errorMessage);
        if (responseReune.getErrores() == null)
        	responseReune.setErrores(new ArrayList<String>());
        responseReune.getErrores().add(errorMessage);
    }
    
    /**
     * Método para dispersar el error en el comsumo del servicio de REUNE.
     * @param e Excepción generada.
     * @param responseReune Respuesta del servicio de REUNE.
     */
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
    
    //trabajar sobre esta captura de excepciones 
    private void handleException(String errorMessage, Exception e, Object response) {
        logger.error(errorMessage, e);
        if (response instanceof ResponseReuneAclaraciones) {
            ResponseReuneAclaraciones responseReuneAclaraciones = (ResponseReuneAclaraciones) response;
            responseReuneAclaraciones.getAclaraciones().clear();
            responseReuneAclaraciones.setError(errorMessage);
            if (responseReuneAclaraciones.getErrores() == null) {
                responseReuneAclaraciones.setErrores(new ArrayList<String>());
            }
            responseReuneAclaraciones.getErrores().add(errorMessage);
        } else if (response instanceof ResponseReuneAclaraciones) {
            ResponseReuneAclaraciones responseReuneAclaraciones = (ResponseReuneAclaraciones) response;
            responseReuneAclaraciones.getAclaraciones().clear();
            responseReuneAclaraciones.setError(errorMessage);
            if (responseReuneAclaraciones.getErrores() == null) {
                responseReuneAclaraciones.setErrores(new ArrayList<String>());
            }
            responseReuneAclaraciones.getErrores().add(errorMessage);
        }
       
    }
    
    private void handleJsonProcessingException(JsonProcessingException e, Object response) {
        String errorMessage = "Error de procesamiento JSON: " + e.getMessage();
        handleException(errorMessage, e, response);
    }
   
    private void handleHttpResponseException(HttpResponseException e, Object response) {
        String errorMessage = "Error HTTP: " + e.getStatusCode() + " - " + e.getMessage() + ":" + e.getResponseBody();
        handleException(errorMessage, e, response);
    }
   
    private void handleNetworkException(NetworkException e, Object response) {
        String errorMessage = "Error de red: " + e.getDetail() + " - " + e.getMessage();
        handleException(errorMessage, e, response);
    }
   
    private void handleUnexpectedException(Exception e, Object response) {
        String errorMessage = "Error inesperado: " + e;
        handleException(errorMessage, e, response);
    }
}