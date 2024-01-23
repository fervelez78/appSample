package mx.bancosabadell.condusef.clients;


import java.util.List;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import mx.bancosabadell.condusef.config.ConfigConstants;
import mx.bancosabadell.condusef.exceptions.HttpResponseException;
import mx.bancosabadell.condusef.exceptions.NetworkException;
import mx.bancosabadell.condusef.models.ResponseRedeco;
import mx.bancosabadell.condusef.services.CondusefBussines;
import mx.bancosabadell.condusef.services.CustomPropertyNamingStrategy;
import mx.bancosabadell.condusef.services.QuejasData;
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
    public ResponseRedeco postQuejas(){
        ResponseRedeco responseRedeco = null;
        logger.info("Inicio request redeco al endpoint: " + ConfigConstants.URL_API_REDECO); 
        
        
        //Se obtine una lista de quejas desde un documento cvs o txt
        List<QuejasData> listQuejasData  = condusefBussines.parseDocumentToBen();
        
        //! Agregar errores de validacion a una excepción personalizada o a errores del response TERMINADO
        responseRedeco = condusefBussines.mapperDocumentQueja(listQuejasData);


        String response;
        System.out.println(responseRedeco.getErrorsValidate() == null);
        if (responseRedeco.getErrorsValidate() == null) {
                // Serializar a JSON usando Jackson
                ObjectMapper objectMapper = new ObjectMapper();
                String requestQuejaTojson = new String();

                try {
                    objectMapper.setPropertyNamingStrategy(new CustomPropertyNamingStrategy());
                    requestQuejaTojson = objectMapper.writeValueAsString(responseRedeco.getQuejas());

                    
                    logger.info("Request del cliente: " + requestQuejaTojson.toString());
                } catch (JsonProcessingException e) {
                    logger.error("Error al procesar el objeto a JSON: {}", e.getMessage(), e);
                }
                
                RequestBody requestBody = RequestBody.create(requestQuejaTojson, MediaType.parse("application/json; charset=utf-8"));
                
                
                try {
                    //!Es posible optener un estado 200 correcto, pero es posible que la peticion no sea sastisfactoria, integrar errores si existen, en la respuesta de salida con un estado fallido
                    response = post(urlBase+pathRedeco, tokenAccess, requestBody);

                    ObjectMapper objectMapperResponse = new ObjectMapper();
                    responseRedeco = objectMapperResponse.readValue(response, ResponseRedeco.class);
                    
                    logger.info("Response redeco endpoin: " + ConfigConstants.URL_API_REDECO + " " + response);
                } catch (HttpResponseException e) {
                    //! agregar errores de exepciones al bean de respuesta
                    // Manejar errores específicos de la respuesta HTTP
                    response ="Error HTTP: " + e.getStatusCode() + " - " + e.getMessage() + ":" +e.getResponseBody();
                    logger.error("Error HTTP: " + e.getStatusCode() + " - " + e.getMessage() + ":" +e.getResponseBody());
                } catch (NetworkException e) {
                    // Manejar errores de red
                    response = "Error de red: " + e.getDetail() + " - " ;
                    logger.error("Error de red: " + e.getDetail() +" - ");
                } catch (Exception e) {
                    // Manejar cualquier otro error
                    response = "Error inesperado: " + e.getMessage();
                    logger.error("Error inesperado: " + e.getMessage());
                }
                
                logger.info("FIN CONSULTA REDECO");
                return responseRedeco;
        
            }else{
                return responseRedeco;
            }
        

        

    }

}
