package mx.bancosabadell.condusef;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mx.bancosabadell.condusef.clients.ClientConducef;
import mx.bancosabadell.condusef.clients.ClientRedeco;
import mx.bancosabadell.condusef.exceptions.ErrorInfoResponse;
import mx.bancosabadell.condusef.models.InfoValidate;
import mx.bancosabadell.condusef.models.ResponseRedeco;
import mx.bancosabadell.condusef.services.Monitor;

public class Condusef{

    private static final Logger logger = LoggerFactory.getLogger("condusefLogger");
    public static void main( String[] args ){
        logger.info("Inicio prueba");
        if (args.length == 0) {
            System.out.println("Debe especificar un método a ejecutar como argumento.");
            return;
        }
        
        // Obtener el nombre del método a ejecutar desde los argumentos
        String methodName = args[0];

        // Ejecutar el método correspondiente
        switch (methodName) {
            case "envioQuejas":
                ClientConducef redeco = new ClientRedeco();
                Monitor monitor = new Monitor();
                envioQuejas(redeco,monitor);
                break;
            case "emailRecordatorio":
                 monitor = new Monitor();
                 emailRecordatorio(monitor);
                break;
            default:
                System.out.println("El método especificado no es válido.");
        }

        
        //ClientConducef reune = new ClientReune();

       
        





    }

       
        private static void envioQuejas(ClientConducef redeco, Monitor monitor) {
        ResponseRedeco response;
        response = redeco.postQuejas();        
        if (response.getErrors() == null) {
            System.out.println("No se obtuvieron errores de redeco");
        
            if (response.getErrorsValidate() == null) {
                // Caso: error de validación
                System.out.println("No se obtuvieron errores de validacion");
            } else {
                // Caso: errores de validación detallados
                System.out.println("Se obtuvieron errores de validacion");
                monitor.createState("false");
                for (InfoValidate string : response.getErrorsValidate()) {
                    System.out.println(string.getMessageError());
                }
            }  
        
            if (response.getError() == null) {
                System.out.println("No se obtuvieron errores");
            } else {
                System.out.println("Se obtuvieron errores");
                monitor.createState("false");
                System.out.println(response.getError());
            }
        } else {
            // Caso: errores generales
            System.out.println("Se optuvieron errores al procesar en condusef");
            for (ErrorInfoResponse string : response.getErrors()) {
                System.out.println(string.getQueja().getErrors() + " : " + string.getQueja().getQuejasFolio());
            }
            //System.out.println(response.toString());
            monitor.createState("false");
        }
    }

    private static void emailRecordatorio(Monitor monitor){
        if (!Boolean.parseBoolean(monitor.readState())) {
            
            System.out.println("Enviar Correo ");

        }else{

            System.out.println("La carga de condusef ha sido fue correcta ");
        }
    }
    

}