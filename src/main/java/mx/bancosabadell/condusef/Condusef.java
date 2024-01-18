package mx.bancosabadell.condusef;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mx.bancosabadell.condusef.clients.ClientConducef;
import mx.bancosabadell.condusef.clients.ClientRedeco;

/**
 * Hello world!
 *
 */
public class Condusef{

    private static final Logger logger = LoggerFactory.getLogger("condusefLogger");
    public static void main( String[] args ){
        logger.info("Inicio prueba");

        ClientConducef redeco = new ClientRedeco();
        //ClientConducef reune = new ClientReune();
        String response = new String();

        
        response = redeco.postQuejas();
        
        
        System.out.println("Response " + response);
        logger.info("Fin prueba");
    }
}
