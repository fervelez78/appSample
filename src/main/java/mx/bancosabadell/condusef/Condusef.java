package mx.bancosabadell.condusef;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mx.bancosabadell.condusef.clients.ClientRedeco;
import mx.bancosabadell.condusef.clients.ClientReune;
import mx.bancosabadell.condusef.config.ConfigConstants;
import mx.bancosabadell.condusef.exceptions.ErrorInfoResponse;
import mx.bancosabadell.condusef.models.InfoValidate;
import mx.bancosabadell.condusef.models.ResponseRedeco;
import mx.bancosabadell.condusef.models.ResponseReune;
import mx.bancosabadell.condusef.services.Monitor;
import mx.bancosabadell.condusef.services.LatiniaServiceEmail;
import mx.bancosabadell.condusef.services.LatiniaServiceImplEmail;

public class Condusef{

    private static final Logger logger = LoggerFactory.getLogger("condusefLogger");
    
    public static void main( String[] args ){
    	System.out.println("Entrando a la aplicación");
        logger.info("Inicio prueba");
        if (args.length == 0) {
        	logger.error("Debe especificar un método a ejecutar como argumento.");
			System.out.println("Debe especificar un método a ejecutar como argumento.: \n1) envioQuejas, \n2) envioConsulta, \n3) emailRecordatorio");
            return;
        }
        
        // Obtener el nombre del método a ejecutar desde los argumentos
        String methodName = args[0];

        // Ejecutar el método correspondiente
        switch (methodName) {
            case "envioQuejas":
                ClientRedeco redeco = new ClientRedeco();
                Monitor monitor = new Monitor();
            	logger.info("REDECO buscando archivos de quejas...");
                envioQuejas(redeco,monitor);
            	//logger.info("REUNE buscando informe trimestral...");
                //ClientConducef reune = new ClientReune();
                break;
				case "envioConsulta":
                ClientReune reune = new ClientReune();
                Monitor monitorConsultas = new Monitor();
               
            	logger.info("REUNE buscando archivos de quejas...");
                envioConsulta(reune, monitorConsultas);            	
                break;
            case "emailRecordatorio":
            	logger.info("Validando si se ha enviado el informe...");
                 monitor = new Monitor();
                 emailRecordatorio(monitor);
                break;
            default:
            	logger.error("El método especificado no es válido.");
        }

    }
    private static void enviaCorreo(String to, String subject, String message) throws Exception{
		/*MailService mail = new LatiniaMailService(); 
		mail.send(to, subject, message);*/ 
    	LatiniaServiceEmail mail = new LatiniaServiceImplEmail();
    	mail.sendEmail(to, subject, message);
    }


    private static void envioQuejas(ClientRedeco redeco, Monitor monitor) {
        try {
	    	ResponseRedeco response;
	        response = redeco.postQuejas();        
	        String asunto = "";
	        String contenido = "";
            monitor.createState("false");
	        // Validamos si hay un error general
	        if (response.getError() != null && !response.getError().equals("")) {
	        	asunto = "Proceso CONDUSEF: Error general";
	        	contenido = "Error: " + response.getError();
	        	logger.error("Se tuvo un error general: " + response.getError());
	        // Validamos si hay errores por ticket
	        } else if (response.getErrors() != null && response.getErrors().size() > 0){
	        	asunto = "Proceso CONDUSEF: Error en REDECO";
	        	String codigos = "";
	            for (ErrorInfoResponse string : response.getErrors()) {
	            	logger.error(string.getQueja().getErrors() + " : " + string.getQueja().getQuejasFolio());
	            	if (codigos.equals(""))
	            		codigos = codigos + ", " + string.getQueja().getQuejasFolio();
	            	else
	            		codigos = string.getQueja().getQuejasFolio();
	            }
	        	contenido = "Folios con error: " + codigos;
	            logger.info("Se obtuvieron errores de redeco " + codigos);
	        // Validacion de errores de validación
	        }else if (response.getErrorsValidate() != null && !response.getErrorsValidate().equals("")) {
                //
	        	asunto = "Proceso CONDUSEF: Error de validación";
	        	logger.error("Se obtuvieron errores de validacion");
	        	String erroresValidacion = "";
	        	for (InfoValidate string : response.getErrorsValidate()) {
                	logger.error(string.getMessageError());
                	erroresValidacion = erroresValidacion + " " + string.getMessageError();
                }
	        	contenido = "Errores: " + erroresValidacion;
	        	
	        }else {
	        	asunto = "Proceso CONDUSEF: Proceso correcto";
	        	contenido = "Se ejeuctó correctamente el archivo de quejas ";
                monitor.createState("true");
	        }
	        	        
    		logger.info("Enviando correo a " + ConfigConstants.CORREO_NOTIF_REDECO);
    		try {
	        	//enviaCorreo(ConfigConstants.CORREO_NOTIF_REDECO, asunto, contenido);
				logger.info("Envio Correo dummi");
    		}catch(Exception ex) {
    			logger.error(ex.getMessage());
    		}

        }catch(Exception e) {
        	String error = "Error inesperado: " + e.getMessage();
        	e.printStackTrace();
        	logger.error(error);
        	for (StackTraceElement trace: e.getStackTrace()) {
        		logger.error(trace.toString());
        	}
    		
    		logger.info("Enviando correo a " + ConfigConstants.CORREO_NOTIF_REDECO);
    		try {
	        	//enviaCorreo(ConfigConstants.CORREO_NOTIF_REDECO, "Proceso CONDUSEF: Error en inesperado", error);
				logger.info("Envio Correo dummi");
    		}catch(Exception ex) {
    			logger.error(ex.getMessage());
    			ex.printStackTrace();
            	for (StackTraceElement trace: e.getStackTrace()) {
            		logger.error(trace.toString());
            	}
    		}
        }
    }

	private static void envioConsulta(ClientReune reune, Monitor monitor) {
        try {
	    	ResponseReune response;
	        response = reune.postConsultasGeneral();        
	        String asunto = "";
	        String contenido = "";
            monitor.createState("false");
	        // Validamos si hay un error general
	        if (response.getError() != null && !response.getError().equals("")) {
	        	asunto = "Proceso CONDUSEF: Error general";
	        	contenido = "Error: " + response.getError();
	        	logger.error("Se tuvo un error general: " + response.getError());
	        // Validamos si hay errores por ticket
	        } else if (response.getErrors() != null && response.getErrors().size() > 0){
	        	asunto = "Proceso CONDUSEF: Error en REDECO";
	        	String codigos = "";
	            for (ErrorInfoResponse string : response.getErrors()) {
	            	logger.error(string.getConsulta().getErrors() + " : " + string.getConsulta().getConsultasFolio());
	            	if (codigos.equals(""))
	            		codigos = codigos + ", " + string.getConsulta().getConsultasFolio();
	            	else
	            		codigos = string.getConsulta().getConsultasFolio();
	            }
	        	contenido = "Folios con error: " + codigos;
	            logger.info("Se obtuvieron errores de redeco " + codigos);
	        // Validacion de errores de validación
	        }else if (response.getErrorsValidate() != null && !response.getErrorsValidate().equals("")) {
                //
	        	asunto = "Proceso CONDUSEF: Error de validación";
	        	logger.error("Se obtuvieron errores de validacion");
	        	String erroresValidacion = "";
	        	for (InfoValidate string : response.getErrorsValidate()) {
                	logger.error(string.getMessageError());
                	erroresValidacion = erroresValidacion + " " + string.getMessageError();
                }
	        	contenido = "Errores: " + erroresValidacion;
	        	
	        }else {
	        	asunto = "Proceso CONDUSEF: Proceso correcto";
	        	contenido = "Se ejeuctó correctamente el archivo de quejas ";
                monitor.createState("true");
	        }
	        	        
    		logger.info("Enviando correo a " + ConfigConstants.CORREO_NOTIF_REDECO);
    		try {
	        	//enviaCorreo(ConfigConstants.CORREO_NOTIF_REDECO, asunto, contenido);
				logger.info("Envio Correo dummi");
    		}catch(Exception ex) {
    			logger.error(ex.getMessage());
    		}

        }catch(Exception e) {
        	String error = "Error inesperado: " + e.getMessage();
        	e.printStackTrace();
        	logger.error(error);
        	for (StackTraceElement trace: e.getStackTrace()) {
        		logger.error(trace.toString());
        	}
    		
    		logger.info("Enviando correo a " + ConfigConstants.CORREO_NOTIF_REDECO);
    		try {
	        	//enviaCorreo(ConfigConstants.CORREO_NOTIF_REDECO, "Proceso CONDUSEF: Error en inesperado", error);
				logger.info("Envio Correo dummi");
    		}catch(Exception ex) {
    			logger.error(ex.getMessage());
    			ex.printStackTrace();
            	for (StackTraceElement trace: e.getStackTrace()) {
            		logger.error(trace.toString());
            	}
    		}
        }
    }

    private static void emailRecordatorio(Monitor monitor){
        if (!Boolean.parseBoolean(monitor.readState())) {
        	logger.info("Enviando Correo de recordatorio " + ConfigConstants.CORREO_NOTIF_REDECO);
    		try {
    			enviaCorreo(ConfigConstants.CORREO_NOTIF_REDECO, "Proceso CONDUSEF: Recordatorio", "No se ha enviado el informe de este mes");
    		}catch(Exception ex) {
    			logger.error(ex.getMessage());
    			ex.printStackTrace();
            	for (StackTraceElement trace: ex.getStackTrace()) {
            		logger.error(trace.toString());
            	}
    		}
        }else{
        	logger.info("La carga de condusef ha sido correcta ");
        }
    }
    

}