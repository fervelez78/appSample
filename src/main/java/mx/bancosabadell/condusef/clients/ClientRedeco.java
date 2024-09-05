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
import mx.bancosabadell.condusef.models.QuejasData;
import mx.bancosabadell.condusef.models.ResponseRedeco;
import mx.bancosabadell.condusef.models.ResponseService;
import mx.bancosabadell.condusef.services.CondusefBussines;
import mx.bancosabadell.condusef.services.CustomPropertyNamingStrategy;
import okhttp3.MediaType;
import okhttp3.RequestBody;


/**
 * Objeto para el uso de la API de REDECO.
 */
public class ClientRedeco extends ClientConducef{

	/**
	 * Logger general de Condusef.
	 */
    private static final Logger logger = LoggerFactory.getLogger("clientRedecoLogger");

    /**
     * Token de acceso para el uso de API REDECO.
     */
    private String tokenAccess = ConfigConstants.getTokenCondusef();
    
    /**
     * Logica de negocio
     */
    private CondusefBussines condusefBussines;
    
    /**
     * Método para recuperar el token del servicio de CONDUSEF.
     * @return Token recuperado.
     * @throws Exception Error generado en caso de que se genere un error al recuperar el Token.
     */
    public String getToken() throws Exception {

    	logger.info("Entrando a getToken");
    	
		UserRequest user = null;
		ObjectMapper mapper = new ObjectMapper();
		logger.info("Razon social: " + getRazonSocial());
    	if (getRazonSocial().trim().toUpperCase().equals("SABADELL")) {
    		logger.info("Entro al usuario de SABADELL: " + ConfigConstants.getUserNameRedecoSabadell());
    		user = new UserRequest(ConfigConstants.getUserNameRedecoSabadell(), ConfigConstants.getPasswordRedecoSabadell());
    	}
        
    	if (getRazonSocial().trim().toUpperCase().equals("SOFOM")) {
    		logger.info("Entro al usuario de SOFOM: " + ConfigConstants.getUserNameRedecoSofom());
    		user = new UserRequest(ConfigConstants.getUserNameRedecoSofom(), ConfigConstants.getPasswordRedecoSofom());
    	}
    	logger.info("user: " + user);
    	String json = mapper.writeValueAsString(user);

		logger.info("JSON: " + json);
		logger.info("URL Token: " + ConfigConstants.getUrlTokenRedeco());
		logger.info("PATH Token: " + ConfigConstants.getPathTokenRedeco());
        // Realizar la petición HTTP
        String response = get(ConfigConstants.getUrlTokenRedeco(),ConfigConstants.getPathTokenRedeco(), json);
		TokenResponse token = mapper.readValue(response, TokenResponse.class);
		if (token.getMsg() == null && token.getMessage() != null && token.getUser() == null) {
			throw new Exception(token.getMessage());
		}
		logger.info("Mensaje: " + token.getMsg());
		logger.info("Token: " + token.getUser().getToken_access());
		return token.getUser().getToken_access();		
    }
    
    /**
     * Método para el consumo del endpoint de consultas de REDECO. 
     * @return Resultado del servicio de quejas.
     */
    public String getQuejas() {
        logger.info("getQuejas");    
        logger.info("Inicio request redeco al endpoint: " + pathRedeco);    
        String response = new String();
        String error = "";
        try {
        	logger.info("Antes de obtener el token: " + tokenAccess);
            tokenAccess = getToken();
            logger.info("Despues de obtener el token: " + tokenAccess);
            response = get(pathRedeco, tokenAccess);
            logger.info("Response redeco endpoin: " + ConfigConstants.getUrlApiRedeco() + "n/"+ response);
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

        logger.info("FIN CONSULTA REDECO");
        return response;
    }

    /**
     * Método para el consumo del endpoint de consultas de REDECO.
     * @return Resultado del servicio de quejas de REDECO.
     * @throws Exception Error generado en el consumo del endpoint.
     */
    public ResponseRedeco postQuejas(String razonSocial) throws Exception{
        logger.info("postQuejas");    
        condusefBussines = new CondusefBussines(razonSocial);
        logger.info("Inicio request redeco al endpoint: " + ConfigConstants.getUrlApiRedeco());
        ResponseRedeco responseRedeco = null;
        setRazonSocial(razonSocial);
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
            	// Obtener token renovado
            	logger.info("Antes de obtener el token");
                tokenAccess = getToken();
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
                //
                if (responseRedeco.getErrors() == null) {
                	if (responseRedeco.getQuejasEnviadas() != null)
                		for (String quejasEnviadas: responseRedeco.getQuejasEnviadas()) {
                			logger.info("Queja enviada: {}", quejasEnviadas);
                		}
                	logger.info("Total de envios: " + responseRedeco.getNumeroTotalDeEnvios());
                	logger.info("Respuesta: " + responseRedeco.getMessage());
                }else if (responseRedeco.getErrors().size() != 0) {
                    List<String> listErrors = new ArrayList<>();
                     for ( ErrorInfoResponse error : responseRedeco.getErrors()) {
                            listErrors.add(error.getQueja().getErrors() + " : " + error.getQueja().getQuejasFolio());

                          /*   System.out.println(error.getQueja().getErrors() + " : " + error.getQueja().getQuejasFolio()); */
                            logger.error(error.getQueja().getErrors() + " : "+ error.getQueja().getQuejasFolio());
                        }
                    
               }
    
                logger.info("Respuesta redeco codigo estado " + response.getCode() + " - endpoint: " + ConfigConstants.getUrlApiRedeco());
    
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

    /**
     * Método para dipsersar un error de REDECO.
     * @param e Excepción generada.
     * @param responseRedeco Resultado del servicio de quejas.
     */
    private void handleJsonProcessingException(JsonProcessingException e, ResponseRedeco responseRedeco) {
        String errorMessage = "Error de procesamiento JSON: " + e.getMessage();
    	logger.error("Error al procesar el objeto a JSON: {}", e.getMessage(), e);
        responseRedeco.getQuejas().clear();
        responseRedeco.setError(errorMessage);
        if (responseRedeco.getErrores() == null)
        	responseRedeco.setErrores(new ArrayList<String>());
        responseRedeco.getErrores().add(errorMessage);
    }
    
    /**
     * Método para dipsersar un error de REDECO.
     * @param e Excepción generada.
     * @param responseRedeco Resultado del servicio de quejas.
     */
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
