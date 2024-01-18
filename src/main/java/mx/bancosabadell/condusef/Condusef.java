package mx.bancosabadell.condusef;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mx.bancosabadell.condusef.clients.ClientConducef;
import mx.bancosabadell.condusef.clients.ClientRedeco;
import mx.bancosabadell.condusef.clients.ClientReune;

/**
 * Hello world!
 *
 */
public class Condusef{
    private static final Logger logger = LoggerFactory.getLogger("condusefLogger");
    public static void main( String[] args ){
        logger.info("Inicio prueba");

        ClientConducef redeco = new ClientRedeco();
        ClientConducef reune = new ClientReune();

        redeco.uploadFile(null, null);
        reune.uploadFile(null, null);
        logger.info("Fin prueba");
    }
}
