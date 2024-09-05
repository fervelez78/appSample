package mx.bancosabadell.condusef.clients;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mx.bancosabadell.red.RestSocket;

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

    protected String urlBase = ConfigConstants.getUrlBase();

    protected String pathRedeco = ConfigConstants.getUrlApiRedeco();

    protected String pathReune = ConfigConstants.getUrlApiReune();

    /**
     * Razon social que realiza el consumo del servicio.
     */
    private String razonSocial;

    String PROXY_MX_BSAB_HOST;
    String PROXY_MX_BSAB_PORT;

    private OkHttpClient clientCondusef;

    public ClientConducef(){
        logger.info("Se crea el cliente de condusef");
        
        Properties properties= new Properties();
        try {
        	ClassLoader loader = Condusef.class.getClassLoader();
        	properties.load(loader.getResourceAsStream("configSystem.properties"));
            PROXY_MX_BSAB_HOST = properties.getProperty("mx.bancosabadell.condusef.proxy.host");
            PROXY_MX_BSAB_PORT = properties.getProperty("mx.bancosabadell.condusef.proxy.port");
        }catch(IOException e) {
        	
            PROXY_MX_BSAB_HOST = "";
            PROXY_MX_BSAB_PORT = "";
        }
        	

        logger.info("Proxy: " + PROXY_MX_BSAB_HOST + ":" + PROXY_MX_BSAB_PORT);
        if (!PROXY_MX_BSAB_HOST.equals("")) {
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(PROXY_MX_BSAB_HOST, Integer.parseInt(PROXY_MX_BSAB_PORT)));

            clientCondusef = new OkHttpClient.Builder()
                    .proxy(proxy)
                    .build();
        }else {
        	clientCondusef = new OkHttpClient.Builder()
            .build();
        }
   }

    //Metodos http get via OkHttp para la comunicacion con conseft
    public String get(String url, String token) throws Exception {

    	
        //Armar el request
        Request request = new Request.Builder()
            .url(url)
            .addHeader(ConfigConstants.getHeaderAuth(), token)
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

    
    public String get(String urlBase, String ruta, String body) throws Exception {
    	RestSocket rest = new RestSocket();
    	logger.info("Proxy " + PROXY_MX_BSAB_HOST + ": " + PROXY_MX_BSAB_PORT);
    	if (PROXY_MX_BSAB_HOST!= null && !PROXY_MX_BSAB_HOST.equals("")) {
    		logger.info("Creando conexión SSL por proxy");
    		rest.buildProxySSl(urlBase, PROXY_MX_BSAB_HOST, Integer.valueOf(PROXY_MX_BSAB_PORT).intValue());
    	}else {
    		logger.info("Creando conexión SSL");
    		rest.buildSSl(urlBase);
    	}
		rest.addProperty("Content-Type", "application/json");
		rest.execute(ruta, body);
		//System.out.println("body:" + rest.getBody());
		return rest.getBody();
    }
    
    //Metodos http get sin token via OkHttp para la comunicacion con conseft
    public String get(String url, RequestBody requestBody) throws Exception {
        
        //Armar el request
        logger.info("Url que forma el request: "  + url);
    	// Armar el request
        Request request = new Request.Builder()
        		  .url(url)
        		  .method("GET", requestBody)
        		  .addHeader("Content-Type", "application/json")
        		  .build();
        //
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
                .addHeader(ConfigConstants.getHeaderAuth(), token)
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

    /**
	 * Recupera la Razon social que realiza el consumo del servicio.
	 * @return Razon social que realiza el consumo del servicio.
	 */
    public String getRazonSocial() {
		return razonSocial;
	}

    /**
     * Define Razon social que realiza el consumo del servicio.
     * @param razonSocial Razon social que realiza el consumo del servicio.
     */
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

}

