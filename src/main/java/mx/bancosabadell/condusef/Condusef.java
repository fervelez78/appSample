package mx.bancosabadell.condusef;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mx.bancosabadell.utils.mail.SMTPMail;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import mx.bancosabadell.condusef.clients.ClientRedeco;
import mx.bancosabadell.condusef.clients.ClientReune;
import mx.bancosabadell.condusef.config.ConfigConstants;
import mx.bancosabadell.condusef.exceptions.ErrorInfoResponse;
import mx.bancosabadell.condusef.models.InfoValidate;
import mx.bancosabadell.condusef.models.ResponseRedeco;
import mx.bancosabadell.condusef.models.ResponseReune;
import mx.bancosabadell.condusef.services.CondusefBussines;
import mx.bancosabadell.condusef.services.Monitor;
//import mx.bancosabadell.condusef.services.LatiniaServiceEmail;
//import mx.bancosabadell.condusef.services.LatiniaServiceImplEmail;


/**
 * Clase del aplicativo Condusef para el envío de reportes a CONDUSEF mediante la API REUNE y REDECO.
 */
public class Condusef{

    /**
     * Logger general de Condusef.
     */
	private static final Logger logger = LoggerFactory.getLogger("condusefLogger");
	
	/**
	 * Logger de REDECO.
	 */
    private static final Logger loggerRedeco = LoggerFactory.getLogger("clientRedecoLogger");
    
    /**
     * Logger de REUNE.
     */
	private static final Logger loggerReune = LoggerFactory.getLogger("clientReuneLogger");
 
	/**
	 * Método inicial del aplicativo.
	 * @param args Se espera un solo parámetro: REUNE, REDECO NOTIFICACION_REDECO o NOTIFICACION_REUNE.  
	 */
    public static void main( String[] args ){

    	logger.info("Entrando a la aplicación Condusef");
        
        // Caso en el que no se tienen parámetros
    	if (args.length == 0) {
        	logger.error("Debe especificar un método a ejecutar como argumento. " +
        		"Debe especificar un método a ejecutar como argumento: \n1) REDECO, \n2) REUNE, \n3) NOTIFICACION_REDECO \n4) NOTIFICACION_REUNE");
            return;
        }
        // Validamos el numero de parámetros
    	if (args == null || args.length < 2)
    		logger.error("Se esperaban dos parámetros: nomAPI y razonSocial.");
        // Obtener el nombre del método a ejecutar desde los argumentos
        String methodName = args[0];
    	String razonSocial = args[1];
    	
        logger.info("methodName: " + methodName);
        logger.info("razonSocial: " + razonSocial);
        // Ejecutar el método correspondiente
        switch (methodName) {
            case "REDECO":
                ClientRedeco redeco = new ClientRedeco();
                Monitor monitor = new Monitor(razonSocial);

				logger.debug("Inicio Carga REDECO");
				envioQuejas(redeco,monitor, razonSocial);
				logger.debug("Fin Carga REDECO");

				logger.info("REDECO buscando archivos");
                break;
			case "REUNE": 
				ClientReune reune = new ClientReune();
				Monitor monitorConsultas = new Monitor(razonSocial);

				logger.debug("Inicio Carga REUNE");
				envioConsulta(reune, monitorConsultas, razonSocial);   
				logger.debug("Fin Carga REUNE"); 
				break;
            case "NOTIFICACION_REDECO":
            	logger.info("Validando si se ha enviado el informe...");
                 monitor = new Monitor(razonSocial);
                 emailRecordatorio(monitor, "REDECO", razonSocial);
                break;
            case "NOTIFICACION_REUNE":
            	logger.info("Validando si se ha enviado el informe...");
                 monitor = new Monitor(razonSocial);
                 emailRecordatorio(monitor, "REUNE", razonSocial);
                break;
            default:
			
		   logger.error("El método especificado no es válido.");
        }

    }
    
    /**
     * Método para enviar un correo mediante el servicio de latinia.
     * @param to Destinatario del correo.
     * @param subject Tema del correo.
     * @param message Mensaje del correo.
     * @throws MessagingException 
     * @throws AddressException 
     * @throws Exception Excepción generada en caso de algun error en el envío. 
     */
    private static void enviaCorreo(String to, String subject, String message) throws AddressException, MessagingException {
		/*
    	// Creación del objeto del correo. 
    	LatiniaServiceEmail mail = new LatiniaServiceImplEmail();
    	// Envío del correo.
    	mail.sendEmail(to, subject, message);
    	*/
		logger.info("Enviando correo " + subject);
    	
    	SMTPMail mail = new SMTPMail();
    	String remitente = ConfigConstants.getCorreoRemitente();
    	String host = ConfigConstants.getSmtpHost();
    	
		for (String destinatario: to.split(";")) {
			// Se inician banderas
			boolean enviado = false;
			int intentos = 1;
			// Se intenta enviar el correo por n intentos
			while (!enviado && intentos <= ConfigConstants.getIntentosEnvioCorreo()) {
				try{
					// Envío de correo
		    		mail.send(
		    			remitente, 
		    			destinatario, 
		    			subject, 
		    			message, 
		    			host);
		    		enviado = true;
				}catch(Exception e) {
					if (intentos + 1 > ConfigConstants.getIntentosEnvioCorreo())
						throw e;
				}
				intentos++;
			}
		}

    }
    
    /**
     * Mueve los archivos procesados a un directorio de salida preestablecido
     * @param dirSalida directorio de salida preestablecido.
     */
    private static void mueveArchivosProcesados(String dirSalida) {
    	for (File archivo: CondusefBussines.archivosProcesados) {
            // Movemos el archivo al histórico
            logger.info("Copiando archivo " + archivo.getPath() + " al directorio " + dirSalida);
            Path origenPath = FileSystems.getDefault().getPath(archivo.getPath());
            Path destinoPath = FileSystems.getDefault().getPath(dirSalida + archivo.getName());
            // Crear la carpeta historico si no existe
            File historico = new File(dirSalida);
            if (!historico.exists()) {
            	logger.info("Creando direcotrio " + historico.getPath());
            	historico.mkdir();
            }
            // Movemos el archivo a la carpeta de histórico
            try {
                Files.move(origenPath, destinoPath, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                logger.info(e.getMessage());
            	for (StackTraceElement trace: e.getStackTrace()) {
            		logger.error(trace.toString());
            	}
            }

    	}
    }
    
    /**
     * Método para el envío de quejas en la API De REDECO.
     * @param redeco Objeto redeco para el envío de quejas.
     * @param monitor Objeto de monitoreo.
     * @param Razon socual que ejecuta el proceso
     */
    private static void envioQuejas(ClientRedeco redeco, Monitor monitor, String razonSocial) {
        loggerRedeco.info("Iniciando proceso de quejas");
        String asunto = "Proceso " + razonSocial + "-REDECO: ";
    	try {
	    	ResponseRedeco response;
	        response = redeco.postQuejas(razonSocial);        
	        String contenido = "";
            monitor.createState("false");
            String dirSalida = ConfigConstants.getDirNas() + ConfigConstants.getDirNasRedeco() + ConfigConstants.getDirNasHistorico();
	        // Validamos si hay un error general
	        if (response.getError() != null && !response.getError().equals("")) {
	        	// Error por request o archivo
	        	if (response.getError().contains("Bad Request")) {
		        	
		        	asunto = asunto + "Error";
		        	contenido = "No se ha logrado el envío del archivo de quejas a la API DE REDECO con el siguiente resultado: \r\n\t" + response.getError() + "\r\n\r\n" + 
		        			"Este correo se genera automáticamente, no responder a esta dirección de correo de servicio de notificación.\r\n";
	        	}else {	        	
	        		asunto = asunto + "Error general";
		        	contenido = "Se ha presentado un error inesperado al momento de tratar enviar el archivo con el siguiente mensaje: \r\n\t" + response.getError() + "\r\n\r\n";
		        	contenido = contenido + "Favor de intentar más tarde.\r\n" + 
		        			"Este correo se genera automáticamente, no responder a esta dirección de correo de servicio de notificación.\r\n";
		        	dirSalida = ConfigConstants.getDirNas() + ConfigConstants.getDirNasReintentos();
	        	}
	        // Validamos si hay errores por ticket
	        } else if (response.getErrors() != null && response.getErrors().size() > 0){
	        	asunto = asunto + "Error";
	        	String codigos = "";
	            for (ErrorInfoResponse string : response.getErrors()) {
	            	logger.error(string.getQueja().getErrors() + " : " + string.getQueja().getQuejasFolio());
					loggerRedeco.error(string.getQueja().getErrors() + " : " + string.getQueja().getQuejasFolio());
	            	if (codigos.equals(""))
	            		codigos = codigos + ", " + string.getQueja().getQuejasFolio();
	            	else
	            		codigos = string.getQueja().getQuejasFolio();
	            }
	        	contenido = "No se ha logrado el envío del archivo de quejas a la API DE REDECO con el siguiente resultado: \r\n\t" + codigos + "\r\n\r\n";
	        	contenido = contenido + "Deberá realizar los ajustes indicados en un nuevo archivo y reintentar nuevamente.\r\n" + 
	        			"Este correo se genera automáticamente, no responder a esta dirección de correo de servicio de notificación.\r\n";
	            logger.info("Se obtuvieron errores de redeco " + codigos);
	            loggerRedeco.info("Se obtuvieron errores de redeco " + codigos);
				
	        // Validacion de errores de validación
	        }else if (response.getErrorsValidate() != null && !response.getErrorsValidate().equals("")) {
                //
	        	asunto = asunto + "Error de validación";
	        	logger.error("Se obtuvieron errores de validacion");
	        	loggerReune.error("Se obtuvieron errores de validacion, envio cancelado");
	        	String erroresValidacion = "";
	        	for (InfoValidate string : response.getErrorsValidate()) {
                	logger.error(string.getMessageError());
                	loggerRedeco.error(string.getMessageError());
                	erroresValidacion = erroresValidacion + " " + string.getMessageError();
                }
	        	contenido = "No se ha logrado el envío del archivo de quejas a la API DE REDECO con el siguiente resultado: \r\n\t" + erroresValidacion + "\r\n\r\n";
	        	contenido = contenido + "Deberá realizar los ajustes indicados en un nuevo archivo y reintentar nuevamente.\r\n" + 
	        			"Este correo se genera automáticamente, no responder a esta dirección de correo de servicio de notificación.";
	        	
	        }else {
	        	asunto = asunto + "Proceso correcto";
	        	contenido = "Se ha enviado correctamente el archivo de quejas a la API DE REDECO con el siguiente resultado: \r\n\t" +
	        			response.toString() + "\r\n\r\n";
	        	contenido = contenido + "No se requiere de algún ajuste o envío adicional.\r\n" + 
	        			"Este correo se genera automáticamente, no responder a esta dirección de correo de servicio de notificación.\r\n";
                monitor.createState("true");
				logger.info(contenido + "\nNúmero total de envios: " + response.getNumeroTotalDeEnvios());
				loggerRedeco.info(contenido + "\n" + response.toString());
	        }
	        // Movemos los archivos procesados a la carpeta de salida
	        mueveArchivosProcesados(dirSalida);
    		logger.info("Enviando correo a " + ConfigConstants.getCorreoNotificaRedeco());
    		try {
	        	enviaCorreo(ConfigConstants.getCorreoNotificaRedeco(), asunto, contenido);
				//logger.info("Envio Correo dummi");
    		}catch(Exception ex) {
    			logger.error(ex.getMessage());
    		}

        }catch(FileNotFoundException e) {
        	logger.error("Error: " + e.getMessage());
        }catch(Exception e) {
        	String error = "Error inesperado: " + e.getMessage();
        	logger.error(error);
        	for (StackTraceElement trace: e.getStackTrace()) {
        		logger.error(trace.toString());
        	}
    		
    		logger.info("Enviando correo a " + ConfigConstants.getCorreoNotificaRedeco());
    		try {
	        	enviaCorreo(ConfigConstants.getCorreoNotificaRedeco(), asunto + "Error en inesperado", error);
				//logger.info("Envio Correo dummi");
    		}catch(Exception ex) {
    			logger.error(ex.getMessage());
    			for (StackTraceElement trace: e.getStackTrace()) {
            		logger.error(trace.toString());
            	}
    		}
        }
    }

    /**
     * Método para el envío de consulta.
     * @param reune Objeto para el envío de reune.
     * @param monitor Objeto de monitoreo.
     * @param Razon social quien ejecuta el proceso 
     */
	private static void envioConsulta(ClientReune reune, Monitor monitor, String razonSocial) {
        // Iniciando el envío del reporte de reune
		loggerReune.info("Iniciando envío de REUNE");
        String dirSalida = ConfigConstants.getDirNas() + ConfigConstants.getDirNasReune() + ConfigConstants.getDirNasHistorico();
        String asunto = "Proceso CONDUSEF-REUNE: ";
		try {
	    	ResponseReune response;
	        response = reune.postConsultasGeneral(razonSocial);        
	        String contenido = "";
            monitor.createState("false");
            // Validamos si hay un error general
	        if (response.getError() != null && !response.getError().equals("")) {
	        	logger.error("Se tuvo un error general: " + response.getError());
	        	// Error por request o archivo
	        	if (response.getError().contains("Bad Request")) {
		        	
		        	asunto = asunto + "Error";
		        	contenido = "No se ha logrado el envío del archivo de quejas a la API DE REUNE con el siguiente resultado: \r\n\t" + response.getError() + "\r\n\r\n" + 
		        			"Este correo se genera automáticamente, no responder a esta dirección de correo de servicio de notificación.\r\n";
	        	}else {	        	
	        		asunto = asunto + "Error general";
		        	contenido = "Se ha presentado un error inesperado al momento de tratar enviar el archivo con el siguiente mensaje: \r\n\t" + response.getError() + "\r\n\r\n";
		        	contenido = contenido + "Favor de intentar más tarde.\r\n" + 
		        			"Este correo se genera automáticamente, no responder a esta dirección de correo de servicio de notificación.\r\n";
		        	dirSalida = ConfigConstants.getDirNas() + ConfigConstants.getDirNasReintentos();
	        	}
	        // Validamos si hay errores por ticket
	        } else if (response.getErrors() != null && response.getErrors().size() > 0){
	        	asunto = asunto + "Error";
	        	String codigos = "";
	            for (ErrorInfoResponse string : response.getErrors()) {
	            	logger.error(string.getConsulta().getErrors() + " : " + string.getConsulta().getConsultasFolio());
					loggerReune.error(string.getConsulta().getErrors() + " : " + string.getConsulta().getConsultasFolio());
	            	if (codigos.equals(""))
	            		codigos = codigos + ", " + string.getConsulta().getConsultasFolio();
	            	else
	            		codigos = string.getConsulta().getConsultasFolio();
	            }
	        	contenido = "No se ha logrado el envío del archivo de quejas a la API DE REUNE con el siguiente resultado: \r\n\t" + codigos + "\r\n\r\n";
	        	contenido = contenido + "Deberá realizar los ajustes indicados en un nuevo archivo y reintentar nuevamente.\r\n" + 
	        			"Este correo se genera automáticamente, no responder a esta dirección de correo de servicio de notificación.\r\n";
	            logger.info("Se obtuvieron errores de reune " + codigos);
	            loggerReune.error("Se obtuvieron errores de reune " + codigos);
				
	        // Validacion de errores de validación
	        }else if (response.getErrorsValidate() != null && !response.getErrorsValidate().equals("")) {
                //
	        	asunto = asunto + "Error de validación";
	        	logger.error("Se obtuvieron errores de validacion");
	        	loggerReune.error("Se obtuvieron errores de validacion, envio cancelado");
	        	String erroresValidacion = "";
	        	for (InfoValidate string : response.getErrorsValidate()) {
                	logger.error(string.getMessageError());
                	loggerReune.error(string.getMessageError());
                	erroresValidacion = erroresValidacion + " " + string.getMessageError();
                }
	        	contenido = "No se ha logrado el envío del archivo de quejas a la API DE REUNE con el siguiente resultado: \r\n\t" + erroresValidacion + "\r\n\r\n";
	        	contenido = contenido + "Deberá realizar los ajustes indicados en un nuevo archivo y reintentar nuevamente.\n" + 
	        			"Este correo se genera automáticamente, no responder a esta dirección de correo de servicio de notificación.";
	        	
	        }else {
	        	asunto = asunto + "Proceso correcto";
	        	contenido = "Se ha enviado correctamente el archivo de quejas a la API DE REUNE con el siguiente resultado: \r\n\t" +
	        			response.toString() + "\r\n\r\n";
	        	contenido = contenido + "No se requiere de algún ajuste o envío adicional.\r\n\r\n" + 
	        			"Este correo se genera automáticamente, no responder a esta dirección de correo de servicio de notificación.\r\n";
                monitor.createState("true");
				loggerReune.info(contenido + "\n" + response.toString());
				logger.info(contenido + "\n" + response.getConsultasEnviadas().toString());
				
	        }
    		logger.info("Enviando correo a " + ConfigConstants.getCorreoNotificaReune());
    		try {
	        	// Envío de correo
    			enviaCorreo(ConfigConstants.getCorreoNotificaReune(), asunto, contenido);
    		}catch(Exception ex) {
    			logger.error(ex.getMessage());
    		}

        }catch(FileNotFoundException e) {
        	logger.error("Error: " + e.getMessage());
        	dirSalida = ConfigConstants.getDirNas() + ConfigConstants.getDirNasReintentos();
        }catch(Exception e) {
        	String error = "Error inesperado: " + e.getMessage();
        	dirSalida = ConfigConstants.getDirNas() + ConfigConstants.getDirNasReintentos();
        	logger.error(error);
        	for (StackTraceElement trace: e.getStackTrace()) {
        		logger.error(trace.toString());
        	}
    		
    		logger.info("Enviando correo a " + ConfigConstants.getCorreoNotificaReune());
    		try {
	        	enviaCorreo(ConfigConstants.getCorreoNotificaReune(), asunto + "Error en inesperado", error);
				//logger.info("Envio Correo dummi");
    		}catch(Exception ex) {
    			logger.error(ex.getMessage());
            	for (StackTraceElement trace: e.getStackTrace()) {
            		logger.error(trace.toString());
            	}
    		}
        }
        // Movemos los archivos procesados a la carpeta de salida
        mueveArchivosProcesados(dirSalida);	        	        
    }

	/**
	 * Método para el envío del correo del recordatorio-
	 * @param monitor Objeto ed monitoreo.
	 * @param proceso Nombre del proceso o recordatorio.
	 */
    private static void emailRecordatorio(Monitor monitor, String proceso, String razonSocial){
    	logger.info("Entrando al correo del recordatorio.");
        if (!Boolean.parseBoolean(monitor.readState())) {
        	logger.info("Enviando Correo de recordatorio " + ConfigConstants.getCorreoNotificaRedeco());
    		try {
				String mensaje = proceso == "REDECO" ? "No se ha enviado el informe para REDECO de este periodo" : "No se ha enviado el informe para REUNE de este periodo";
    			enviaCorreo(
					ConfigConstants.getCorreoNotificaRedeco(),
				 	"Proceso " + razonSocial + "-" + proceso + ": \n Recordatorio:",
				    mensaje);

    		}catch(Exception ex) {
    			logger.error(ex.getMessage());
            	for (StackTraceElement trace: ex.getStackTrace()) {
            		logger.error(trace.toString());
            	}
    		}
        }else{
        	logger.info("La carga de condusef ha sido correcta ");
        }
    }
    

}