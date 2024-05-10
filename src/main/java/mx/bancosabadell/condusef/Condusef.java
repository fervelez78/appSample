package mx.bancosabadell.condusef;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

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
    private static final Logger loggerRedeco = LoggerFactory.getLogger("clientRedecoLogger");
	private static final Logger loggerReune = LoggerFactory.getLogger("clientReuneLogger");
    /*
	public Properties loadPropertiesFile(String filePath) {

        Properties prop = new Properties();

        try (InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream(filePath)) {
            prop.load(resourceAsStream);
        } catch (IOException e) {
            System.err.println("Unable to load properties file : " + filePath);
        }

        return prop;

    }*/
	
    public static void main( String[] args ){
    	System.out.println("Entrando a la aplicación");
        logger.info("Inicio prueba");
        
        /*Condusef app = new Condusef();
        
        
        Properties prop = app.loadPropertiesFile("configSystem.properties");
        prop.forEach((k, v) -> System.out.println(k + ":" + v));
        */
        
        if (args.length == 0) {
        	logger.error("Debe especificar un método a ejecutar como argumento.");
			System.out.println("Debe especificar un método a ejecutar como argumento.: \n1) REDECO, \n2) REUNE, \n3) NOTIFICACION_REDECO \n4) NOTIFICACION_REUNE");
            return;
        }
        
        // Obtener el nombre del método a ejecutar desde los argumentos
        String methodName = args[0];
        logger.info("methodName: " + methodName);
        // Ejecutar el método correspondiente
        switch (methodName) {
            case "REDECO":
                ClientRedeco redeco = new ClientRedeco();
                Monitor monitor = new Monitor();

				System.out.println("Inicio Carga REDECO");
				envioQuejas(redeco,monitor);
				System.out.println("Fin Carga REDECO");

				

            	logger.info("REDECO buscando archivos");
				
			
                break;
				case "REUNE" : 
					ClientReune reune = new ClientReune();
					Monitor monitorConsultas = new Monitor();

					System.out.println("Inicio Carga REUNE");
					envioConsulta(reune, monitorConsultas);   
					System.out.println("Fin Carga REUNE"); 
				break;
            case "NOTIFICACION_REDECO":
            	logger.info("Validando si se ha enviado el informe...");
                 monitor = new Monitor();
                 emailRecordatorio(monitor, "REDECO");
                break;
            case "NOTIFICACION_REUNE":
            	logger.info("Validando si se ha enviado el informe...");
                 monitor = new Monitor();
                 emailRecordatorio(monitor, "REUNE");
                break;
			/* case "REUNE/REDECO":
			redeco = new ClientRedeco();
			monitor = new Monitor();

			reune = new ClientReune();
			monitorConsultas = new Monitor();
		   try {
			   List<String> namesFiles = monitor.readFolders();

			   for (String nameFile : namesFiles) {
				   switch (nameFile) {
					   case  ConfigConstants.NAME_FILE_REDECO:
						   logger.info("REDECO buscando archivos de quejas... ");
						   System.out.println("Inicio Carga REDECO");
						   envioQuejas(redeco,monitor);
						   System.out.println("Fin Carga REDECO");
						   break;
					   case ConfigConstants.NAME_FILE_REUNE:
						   logger.info("REDECO buscando archivos de consultas... ");
						   System.out.println("Inicio Carga REUNE");
						   envioConsulta(reune, monitorConsultas);   
						   System.out.println("Fin Carga REUNE"); 
					   default:
					   logger.info(nameFile);
						   break;
				   }
			   }

		   } catch (Exception e) {
			   
			   e.printStackTrace();
		   }      
			break; */
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
					loggerRedeco.error(string.getQueja().getErrors() + " : " + string.getQueja().getQuejasFolio());
	            	if (codigos.equals(""))
	            		codigos = codigos + ", " + string.getQueja().getQuejasFolio();
	            	else
	            		codigos = string.getQueja().getQuejasFolio();
	            }
	        	contenido = "Folios con error: " + codigos;
	            logger.info("Se obtuvieron errores de redeco " + codigos);
	            loggerRedeco.info("Se obtuvieron errores de redeco " + codigos);
				
	        // Validacion de errores de validación
	        }else if (response.getErrorsValidate() != null && !response.getErrorsValidate().equals("")) {
                //
	        	asunto = "Proceso CONDUSEF: Error de validación";
	        	logger.error("Se obtuvieron errores de validacion");
	        	loggerReune.error("Se obtuvieron errores de validacion, envio cancelado");
	        	String erroresValidacion = "";
	        	for (InfoValidate string : response.getErrorsValidate()) {
                	logger.error(string.getMessageError());
                	loggerRedeco.error(string.getMessageError());
                	erroresValidacion = erroresValidacion + " " + string.getMessageError();
                }
	        	contenido = "Errores: " + erroresValidacion;
	        	
	        }else {
	        	asunto = "Proceso CONDUSEF: Proceso correcto";
	        	contenido = "Se ejeuctó correctamente el archivo de quejas ";
                monitor.createState("true");
				logger.info(contenido + "\nNúmero total de envios: " + response.getNumeroTotalDeEnvios());
				loggerRedeco.info(contenido + "\n" + response.toString());
	        }
	        	        
    		logger.info("Enviando correo a " + ConfigConstants.CORREO_NOTIF_REDECO);
    		try {
	        	enviaCorreo(ConfigConstants.CORREO_NOTIF_REDECO, asunto, contenido);
				//logger.info("Envio Correo dummi");
    		}catch(Exception ex) {
    			logger.error(ex.getMessage());
    		}

        }catch(FileNotFoundException e) {
        	logger.error("Error: " + e.getMessage());
        }catch(Exception e) {
        	String error = "Error inesperado: " + e.getMessage();
        	e.printStackTrace();
        	logger.error(error);
        	for (StackTraceElement trace: e.getStackTrace()) {
        		logger.error(trace.toString());
        	}
    		
    		logger.info("Enviando correo a " + ConfigConstants.CORREO_NOTIF_REDECO);
    		try {
	        	enviaCorreo(ConfigConstants.CORREO_NOTIF_REDECO, "Proceso CONDUSEF: Error en inesperado", error);
				//logger.info("Envio Correo dummi");
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
	        	asunto = "Proceso CONDUSEF: Error en REUNE";
	        	String codigos = "";
	            for (ErrorInfoResponse string : response.getErrors()) {
	            	logger.error(string.getConsulta().getErrors() + " : " + string.getConsulta().getConsultasFolio());
					loggerReune.error(string.getConsulta().getErrors() + " : " + string.getConsulta().getConsultasFolio());
	            	if (codigos.equals(""))
	            		codigos = codigos + ", " + string.getConsulta().getConsultasFolio();
	            	else
	            		codigos = string.getConsulta().getConsultasFolio();
	            }
	        	contenido = "Folios con error: " + codigos;
	            logger.info("Se obtuvieron errores de reune " + codigos);
	            loggerReune.error("Se obtuvieron errores de reune " + codigos);
				
	        // Validacion de errores de validación
	        }else if (response.getErrorsValidate() != null && !response.getErrorsValidate().equals("")) {
                //
	        	asunto = "Proceso CONDUSEF: Error de validación";
	        	logger.error("Se obtuvieron errores de validacion");
	        	loggerReune.error("Se obtuvieron errores de validacion, envio cancelado");
	        	String erroresValidacion = "";
	        	for (InfoValidate string : response.getErrorsValidate()) {
                	logger.error(string.getMessageError());
                	loggerReune.error(string.getMessageError());
                	erroresValidacion = erroresValidacion + " " + string.getMessageError();
                }
	        	contenido = "Errores: " + erroresValidacion;
	        	
	        }else {
	        	asunto = "Proceso CONDUSEF: Proceso correcto";
	        	contenido = "Se ejeuctó correctamente el archivo de consultas ";
                monitor.createState("true");
				loggerReune.info(contenido + "\n" + response.toString());
				logger.info(contenido + "\n" + response.getConsultasEnviadas().toString());
				
	        }
	        	        
    		logger.info("Enviando correo a " + ConfigConstants.CORREO_NOTIF_REUNE);
    		try {
	        	enviaCorreo(ConfigConstants.CORREO_NOTIF_REDECO, asunto, contenido);
				//logger.info("Envio Correo dummi");
    		}catch(Exception ex) {
    			logger.error(ex.getMessage());
    		}

        }catch(FileNotFoundException e) {
        	logger.error("Error: " + e.getMessage());
        }catch(Exception e) {
        	String error = "Error inesperado: " + e.getMessage();
        	e.printStackTrace();
        	logger.error(error);
        	for (StackTraceElement trace: e.getStackTrace()) {
        		logger.error(trace.toString());
        	}
    		
    		logger.info("Enviando correo a " + ConfigConstants.CORREO_NOTIF_REDECO);
    		try {
	        	enviaCorreo(ConfigConstants.CORREO_NOTIF_REDECO, "Proceso CONDUSEF: Error en inesperado", error);
				//logger.info("Envio Correo dummi");
    		}catch(Exception ex) {
    			logger.error(ex.getMessage());
    			ex.printStackTrace();
            	for (StackTraceElement trace: e.getStackTrace()) {
            		logger.error(trace.toString());
            	}
    		}
        }
    }

    private static void emailRecordatorio(Monitor monitor, String proceso){
        if (!Boolean.parseBoolean(monitor.readState())) {
        	logger.info("Enviando Correo de recordatorio " + ConfigConstants.CORREO_NOTIF_REDECO);
    		try {
				String mensaje = proceso == "REDECO" ? "No se ha enviado el informe para REDECO de este periodo" : "No se ha enviado el informe para REUNE de este periodo";
    			enviaCorreo(
					ConfigConstants.CORREO_NOTIF_REDECO,
				 	"Proceso CONDUSEF" + proceso + ": \n Recordatorio:",
				    mensaje);

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