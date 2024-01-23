package mx.bancosabadell.condusef;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mx.bancosabadell.condusef.clients.ClientConducef;
import mx.bancosabadell.condusef.clients.ClientRedeco;
import mx.bancosabadell.condusef.models.ErrorInfoResponse;
import mx.bancosabadell.condusef.models.ResponseRedeco;

public class Condusef{

    private static final Logger logger = LoggerFactory.getLogger("condusefLogger");
    public static void main( String[] args ){
        logger.info("Inicio prueba");

        ClientConducef redeco = new ClientRedeco();
        //ClientConducef reune = new ClientReune();

       
        ResponseRedeco response = new ResponseRedeco();        
        response = redeco.postQuejas();
        
        for ( ErrorInfoResponse string : response.getErrors()) {
            System.out.println(string.getQueja().getErrors() + " : "+string.getQueja().getQuejasFolio());
        }   
        logger.info("Fin prueba");
    }

}