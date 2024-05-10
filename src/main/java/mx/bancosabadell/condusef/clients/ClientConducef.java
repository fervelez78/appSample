package mx.bancosabadell.condusef.clients;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mx.bancosabadell.condusef.Condusef;
import mx.bancosabadell.condusef.config.ConfigConstants;
import mx.bancosabadell.condusef.exceptions.HttpResponseException;
import mx.bancosabadell.condusef.exceptions.NetworkException;
import mx.bancosabadell.condusef.models.ResponseService;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public abstract class ClientConducef {    

    private static final Logger logger = LoggerFactory.getLogger("condusefLogger");

    protected String urlBase = ConfigConstants.URL_BASE;

    protected String pathRedeco = ConfigConstants.URL_API_REDECO;

    protected String pathReune = ConfigConstants.URL_API_REUNE;
    
    private OkHttpClient clientCondusef;

    public ClientConducef(){
        logger.info("Se crea el cliente de condusef");
        
        Properties properties= new Properties();
    	try {
        	ClassLoader loader = Condusef.class.getClassLoader();
        	properties.load(loader.getResourceAsStream("configSystem.properties"));
        	
            String PROXY_MX_BSAB_HOST = properties.getProperty("mx.bancosabadell.condusef.proxy.host");
            String PROXY_MX_BSAB_PORT = properties.getProperty("mx.bancosabadell.condusef.proxy.port");

            logger.info("Proxy: " + PROXY_MX_BSAB_HOST + ":" + PROXY_MX_BSAB_PORT);
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(PROXY_MX_BSAB_HOST, Integer.parseInt(PROXY_MX_BSAB_PORT)));

            clientCondusef = new OkHttpClient.Builder()
                    .proxy(proxy)
                    .build();
    	}catch(IOException e) {
    		e.printStackTrace();
            clientCondusef = new OkHttpClient.Builder()
                    .build();
    	}
   }

    //Metodos http get via OkHttp para la comunicacion con conseft
    public String get(String url, String token) throws Exception {

        
        //Armar el request
        Request request = new Request.Builder()
                .url(url)
                .addHeader(ConfigConstants.HEADER_AUTH, token)
                .build();

            try (Response response = clientCondusef.newCall(request).execute()) {
                if (!response.isSuccessful()){
                // Manejar excepciones de con el servicio api condusef
                logger.error(response.code() +  " Error en la respuesta HTTP: " + response.message() + " " + response.body().string());
                throw new HttpResponseException(response.code(), "Error en la respuesta HTTP: " + response.message(), response.body().string());
            }
            logger.info("Se termina el request con exito " + response.code());
            return response.body().string();            
        }catch (IOException e) {
        	logger.error("Error de red", e, e.getMessage());
        	// Manejar excepciones de I/O, como problemas de red
            throw new NetworkException("Error de red", e, e.getMessage());
        }

    }

    //Metodos http get sin token via OkHttp para la comunicacion con conseft
    public String get(String url) throws Exception {

        
        //Armar el request
        logger.info("Url que forma el request: "  + url);
        Request request = new Request.Builder()
                .url(url)
                .build();

            try (Response response = clientCondusef.newCall(request).execute()) {
                if (!response.isSuccessful()){
                // Manejar excepciones de con el servicio api condusef
                logger.error(response.code() +  " Error en la respuesta HTTP: " + response.message() + " " + response.body().string());
                throw new HttpResponseException(response.code(), "Error en la respuesta HTTP: " + response.message(), response.body().string());
            }
            logger.info("Se termina el request con exito " + response.code());
            return response.body().string();            
        }catch (IOException e) {
        	// Manejar excepciones de I/O, como problemas de red
        	logger.error("Error de red", e, e.getMessage());
            throw new NetworkException("Error de red", e, e.getMessage());
        }

    }

    

    //Metodos http post via OkHttp para la comunicacion con conseft
    public ResponseService post(String url, String token, RequestBody requestBody) throws HttpResponseException, NetworkException {
        //
    	logger.info("url: " + url);
    	logger.info("token: " + token);

    	// Armar el request
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .addHeader(ConfigConstants.HEADER_AUTH, token)
                .build();
    
        Response response = null;
    
        
        try {
            response = clientCondusef.newCall(request).execute();
            
            ResponseService responseRedecoService = new ResponseService(response.body().string(), response.code(), response.message());
            
            if (!response.isSuccessful()) {
                
                 // Manejar excepciones con el servicio API Condusef
                 String responseBody = responseRedecoService.getBody(); // Almacenar el cuerpo en una variable
                 String errorMessage = responseRedecoService.getCode() + " Error en la respuesta HTTP: " + responseRedecoService.getMessage() + " " + responseBody;
                 
                 logger.error(errorMessage);
                 throw new HttpResponseException(responseRedecoService.getCode(), responseRedecoService.getMessage(), responseBody);
                }
                
    
            logger.info("Se termina el request con éxito " + response.code());
            return responseRedecoService;
    
        } catch (HttpResponseException e) {
            // Propagar la excepción HttpResponseException
        	logger.error("Error response", e, e.getMessage());
            throw e;
    
        } catch (IOException e) {
            // Manejar excepciones de I/O, como problemas de red
            String errorMessage = "Error de red: " + e.getMessage();
            logger.error(errorMessage, e);
            throw new NetworkException(errorMessage, e, e.getMessage());
    
        } finally {
            // Cerrar la respuesta en cualquier caso
            if (response != null) {
                response.close();
            }
        }
    }
    

    public String post(String url,RequestBody requestBody) throws Exception {

        //Armar el request
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

        try (Response response = clientCondusef.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                 // Manejar excepciones de con el servicio api condusef
                logger.error(response.code() +  " Error en la respuesta HTTP: " + response.message() + " " + response.body().string());
                throw new Exception("Unexpected code " + response);
            }
            logger.info("Se termina el request con exito " + response.code());
            return response.body().string();
        }catch (IOException e) {
            // Manejar excepciones de I/O, como problemas de red
        	logger.error("Error de red", e, e.getMessage());
            throw new NetworkException("Error de red", e, e.getMessage());
        }

    }
}

