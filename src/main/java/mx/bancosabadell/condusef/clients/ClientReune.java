package mx.bancosabadell.condusef.clients;

import java.io.File;

import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mx.bancosabadell.condusef.config.ConfigConstants;

public class ClientReune extends ClientConducef{

    
    
    private static final Logger logger = LoggerFactory.getLogger("clientReuneLogger");
    @Override
    public HttpResponse uploadFile(File file,String ulr_service) {
        logger.info("INICIO CARGA Reune " +  ConfigConstants.URL_API_REUNE);

        System.out.println("REUNE " + ConfigConstants.URL_API_REUNE);
        logger.info("FIN CARGA REDECO");
        return null;
    }

}
