package mx.bancosabadell.condusef.clients;


import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import mx.bancosabadell.condusef.config.ConfigConstants;
import mx.bancosabadell.condusef.exceptions.HttpResponseException;
import mx.bancosabadell.condusef.exceptions.NetworkException;
import mx.bancosabadell.condusef.models.Queja;
import mx.bancosabadell.condusef.services.CondusefBussines;
import mx.bancosabadell.condusef.services.CustomPropertyNamingStrategy;
import okhttp3.MediaType;
import okhttp3.RequestBody;


public class ClientRedeco extends ClientConducef{

    private static final Logger logger = LoggerFactory.getLogger("clientRedecoLogger");

    private String tokenAccess = ConfigConstants.TOKEN_CONDUSEF;

    
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
    public String postQuejas(){
        logger.info("Inicio request redeco al endpoint: " + ConfigConstants.URL_API_REDECO); 

        //Instanciar queja dommi         
        CondusefBussines condusefBussines = new CondusefBussines();
        List<Queja> quejas = new ArrayList<>();
        Queja queja = new Queja();
        
        try {
            queja = condusefBussines.mapperDocumentQueja();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        quejas.add(queja);

        // Serializar a JSON usando Jackson
        ObjectMapper objectMapper = new ObjectMapper();
        String requestQuejaTojson = new String();

        try {
            objectMapper.setPropertyNamingStrategy(new CustomPropertyNamingStrategy());
            requestQuejaTojson = objectMapper.writeValueAsString(quejas);
            logger.info("Request del cliente: " + requestQuejaTojson.toString());
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        RequestBody requestBody = RequestBody.create(requestQuejaTojson, MediaType.parse("application/json; charset=utf-8"));

        String response = new String();
        try {
            response = post(urlBase+pathRedeco, tokenAccess, requestBody);
            logger.info("Response redeco endpoin: " + ConfigConstants.URL_API_REDECO + " " + response);
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
        System.out.println("REDECO " + ConfigConstants.URL_API_REDECO);
        logger.info("FIN CONSULTA REDECO");
        return response;
    }

    

}
