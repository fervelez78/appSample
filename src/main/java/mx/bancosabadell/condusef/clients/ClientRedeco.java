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
import mx.bancosabadell.condusef.models.Queja;
import mx.bancosabadell.condusef.models.QuejasData;
import mx.bancosabadell.condusef.models.ResponseRedeco;
import mx.bancosabadell.condusef.models.ResponseRedecoService;
import mx.bancosabadell.condusef.services.CondusefBussines;
import mx.bancosabadell.condusef.services.CustomPropertyNamingStrategy;
import okhttp3.MediaType;
import okhttp3.RequestBody;


public class ClientRedeco extends ClientConducef{

    private static final Logger logger = LoggerFactory.getLogger("clientRedecoLogger");

    private String tokenAccess = ConfigConstants.TOKEN_CONDUSEF;

    //Logica de negocio
    private CondusefBussines condusefBussines = new CondusefBussines();
    
    @Override
    public String getQuejas() {
        logger.info("Inicio request redeco al endpoint: " + ConfigConstants.URL_API_REDECO);    
        String response = new String();
        try {
            response = get(urlBase+pathRedeco, tokenAccess);
            logger.info("Response redeco endpoin: " + ConfigConstants.URL_API_REDECO + "n/"+ response);
        } catch (HttpResponseException e) {
            // Manejar errores específicos de la respuesta HTTP
            logger.error("Error HTTP: " + e.getStatusCode() + " - " + e.getMessage() + ":" +e.getResponseBody());
        } catch (NetworkException e) {
            // Manejar errores de red
            logger.error("Error de red: " + e.getDetail() +" - ");
        } catch (Exception e) {
            // Manejar cualquier otro error
            logger.error("Error inesperado: " + e.getMessage());
        }
        logger.info("FIN CONSULTA REDECO");
        return response;
    }

    
    @Override
    public ResponseRedeco postQuejas() {
        logger.info("Inicio request redeco al endpoint: " + ConfigConstants.URL_API_REDECO);
    
        // Se obtiene una lista de quejas cvs desde un documento cvs o txt
        List<QuejasData> listQuejasData = condusefBussines.parseDocumentToBenXlsx();
    
        // Se obtiene un ResponseRedeco con lista de quejas que se mapean desde la lista de quejas cvs
        
        ResponseRedeco responseRedeco = condusefBussines.mapperDocumentQueja(listQuejasData);
        
        List<Queja> quejas = new ArrayList<>();

       
        // Verificar si hay errores de validación
        if (responseRedeco.getErrorsValidate() == null && responseRedeco.getError() == null) {
            try {
                // Serializar a JSON usando Jackson
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.setPropertyNamingStrategy(new CustomPropertyNamingStrategy());
                String requestQuejaTojson = objectMapper.writeValueAsString(/* quejas */responseRedeco.getQuejas());
    
                //logger.info("Request del cliente: " + requestQuejaTojson);
    
                // Crear el request que se mandará en la petición http
                RequestBody requestBody = RequestBody.create(requestQuejaTojson, MediaType.parse("application/json; charset=utf-8"));
    
                // Realizar la petición HTTP
                ResponseRedecoService response = post(urlBase + pathRedeco, tokenAccess, requestBody);
                String responseToString = response.getBody();
                // Parsear la respuesta a ResponseRedeco
                ObjectMapper objectMapperResponse = new ObjectMapper();
                responseRedeco = objectMapperResponse.readValue(responseToString, ResponseRedeco.class);
                if (responseRedeco.getErrors().size() != 0) {
                    List<String> listErrors = new ArrayList<>();
                     for ( ErrorInfoResponse error : responseRedeco.getErrors()) {
                            listErrors.add(error.getQueja().getErrors() + " : " + error.getQueja().getQuejasFolio());

                          /*   System.out.println(error.getQueja().getErrors() + " : " + error.getQueja().getQuejasFolio()); */
                            logger.info(error.getQueja().getErrors() + " : "+ error.getQueja().getQuejasFolio());
                        }
                    
                }
    
                logger.info("Respuesta redeco codigo estado " + response.getCode() + " - endpoint: " + ConfigConstants.URL_API_REDECO);
    
            } catch (JsonProcessingException e) {
                // Manejar errores de procesamiento JSON
                handleJsonProcessingException(e, responseRedeco);
    
            } catch (HttpResponseException e) {
                // Manejar errores específicos de la respuesta HTTP
                handleHttpResponseException(e, responseRedeco);
    
            } catch (NetworkException e) {
                // Manejar errores de red
                handleNetworkException(e, responseRedeco);
    
            } catch (Exception e) {
                // Manejar cualquier otro error
                handleUnexpectedException(e, responseRedeco);
            }
    
            logger.info("FIN CONSULTA REDECO");
        }
    
        return responseRedeco;

    }

    private void handleJsonProcessingException(JsonProcessingException e, ResponseRedeco responseRedeco) {
        logger.error("Error al procesar el objeto a JSON: {}", e.getMessage(), e);
        responseRedeco.getQuejas().clear();
        responseRedeco.setError("Error de procesamiento JSON: " + e.getMessage());
    }
    
    private void handleHttpResponseException(HttpResponseException e, ResponseRedeco responseRedeco) {
        String errorMessage = "Error HTTP: " + e.getStatusCode() + " - " + e.getMessage() + ":" + e.getResponseBody();
        logger.error(errorMessage);
        responseRedeco.getQuejas().clear();
        responseRedeco.setError(errorMessage);
    }
    
    private void handleNetworkException(NetworkException e, ResponseRedeco responseRedeco) {
        String errorMessage = "Error de red: " + e.getDetail() + " - " + e.getMessage();
        logger.error(errorMessage);
        responseRedeco.getQuejas().clear();
        responseRedeco.setError(errorMessage);
    }
    
    private void handleUnexpectedException(Exception e, ResponseRedeco responseRedeco) {
        String errorMessage = "Error inesperado: " + e;
        logger.error(errorMessage, e);
        responseRedeco.getQuejas().clear();
        responseRedeco.setError(errorMessage);
    }
    
}
