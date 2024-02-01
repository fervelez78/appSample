package mx.bancosabadell.condusef;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mx.bancosabadell.condusef.clients.ClientConducef;
import mx.bancosabadell.condusef.clients.ClientRedeco;
import mx.bancosabadell.condusef.exceptions.ErrorInfoResponse;
import mx.bancosabadell.condusef.models.InfoValidate;
import mx.bancosabadell.condusef.models.ResponseRedeco;

public class Condusef{

    private static final Logger logger = LoggerFactory.getLogger("condusefLogger");
    public static void main( String[] args ){
        logger.info("Inicio prueba");

        ClientConducef redeco = new ClientRedeco();
        //ClientConducef reune = new ClientReune();

       
        ResponseRedeco response = new ResponseRedeco();        
        response = redeco.postQuejas();

        
        if (response.getErrors() == null) {
            System.out.println("No se obtuvieron errores de redeco");
            if (response.getErrorsValidate() == null) {
                // Caso: error de validación
                System.out.println("No se obtuvieron errores de validacion");

                System.out.println(response);
                
            } else {
                // Caso: errores de validación detallados
                System.out.println("Se obtuvieron errores de validacion");
                for (InfoValidate string : response.getErrorsValidate()) {
                    System.out.println(string.getMessageError());
                }
            }            
        } else {
            // Caso: errores generales
            for (ErrorInfoResponse string : response.getErrors()) {
                System.out.println(string.getQueja().getErrors() + " : " + string.getQueja().getQuejasFolio());
            }
        }
        
        
        
        

        logger.info("Fin prueba");
    }

}