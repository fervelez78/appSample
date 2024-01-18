package mx.bancosabadell.condusef.clients;

import java.io.File;

import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mx.bancosabadell.condusef.config.ConfigConstants;

public class ClientRedeco extends ClientConducef{

    private static final Logger logger = LoggerFactory.getLogger("clientRedecoLogger");


    @Override
    public HttpResponse uploadFile(File file,String ulr_service) {
        logger.info("INICIO CARGA REDECO " + ConfigConstants.URL_API_REDECO);

        

        System.out.println("REDECO " + ConfigConstants.URL_API_REDECO);
        logger.info("FIN CARGA REDECO");
        return null;
    }

    

}
