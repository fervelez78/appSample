package mx.bancosabadell.condusef.clients;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mx.bancosabadell.condusef.config.ConfigConstants;

public class ClientReune extends ClientConducef{

    
    
    private static final Logger logger = LoggerFactory.getLogger("clientReuneLogger");
    @Override
    public String getQuejas() {
        logger.info("INICIO CARGA Reune " +  ConfigConstants.URL_API_REUNE);

        System.out.println("REUNE " + ConfigConstants.URL_API_REUNE);
        logger.info("FIN CARGA Reune");
        return null;
    }
    @Override
    public String postQuejas() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'postQuejas'");
    }

}
