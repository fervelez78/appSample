package mx.bancosabadell.condusef.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * Clase para recuperar las propiedades de de configuración.
 */
public class ConfigConstants {


	/**
	 * Directorio NAS donde se depositará el archivo origen
	 */
    private static String dirNas;
    
    /**
     * Directorio donde se depositarán los logs.
     */
    private static String dirNasLog;
    
    /**
     * Directorio donde se buscará el archivo REDECO.
     */
    private static String dirNasRedeco;
    
    /**
     * Directorio donde se buscará el archivo REUNE.
     */
    private static String dirNasReune;
    
    /**
     * Directorio donde se guardará los archivos procesados.
     */
    private static String dirNasHistorico;
    
	/**
     * Directorio donde se guardarán los tickets.
     */
    private static String dirNasTicket;

    /**
     * Correos de notificación de los resultados para REDECO.
     */
    private static String correoNotificaRedeco;
    
    /**
     * Correos de notificación de los resultados para REUNE.
     */
    private static String correoNotificaReune;
    
    /**
     * Dirección del servicio para el envío del correo.
     */
    private static String urlServMail;
    
    /**
     * Dirección de la ruta base.
     */
    private static String urlBase;
    
    /**
     * Dirección de la API REUNE.
     */
    private static String urlApiReune;
    
    /**
     * Dirección de la API REDECO.
     */
    private static String urlApiRedeco;
    
    /**
     * Dirección para obtener el token REDECO.
     */
    private static String urlTokenRedeco;
    
    /**
     * Dirección del token REDECO.
     */
    private static String pathTokenRedeco;

    /**
     * Dirección para obtener el token REUNE.
     */
    private static String urlTokenReune;
    
    /**
     * Dirección del token REUNE.
     */
    private static String pathTokenReune;

    /**
     * Token fijo de CONDUSEF.
     */
    private static String tokenCondusef;

    /**
     * Ubicación del archivo de configuración.
     */
    private static String urlConfigFile = "src\\main\\resources\\config.properties";

    /**
     * Nombre del parámetro de autorización.
     */
    private static String headerAuth = "Authorization";

    /**
     * Expresión regular para obtener el archivo de REDECO Sabadell.
     */
    private static String regExpQuejasRedecoSabadell;
    
    /**
     * Expresión regular para obtener el archivo de REDECO Sofom.
     */
    private static String regExpQuejasRedecoSofom;

    /**
     * Expresión regular para obtener el archivo de REUNE Sabadell.
     */
    private static String regExpQuejasReuneSabadell;
    
    /**
     * Expresión regular para obtener el archivo de REUNE Sfom.
     */
    private static String regExpQuejasReuneSofom;
    
    /**
     * Nombre de usuario REDECO para SABADELL.
     */
    private static String userNameRedecoSabadell;

    /**
     * Nombre de usuario REDECO para SOFOM.
     */
    private static String userNameRedecoSofom;
    
    /**
     * Contraseña del usuario de REDECO de Sabadell.
     */
    private static String passwordRedecoSabadell;

    /**
     * Contraseña del usuario de REDECO de Sofom.
     */
    private static String passwordRedecoSofom;

    /**
     * Nombre de usuario REUNE Sabadell.
     */
    private static String userNameReuneSabadell;
    
    /**
     * Contraseña del usuario de REUNE Sofom.
     */
    private static String passwordReuneSofom;

    /**
     * Nombre de usuario REUNE Dogom.
     */
    private static String userNameReuneSofom;
    
    /**
     * Contraseña del usuario de REUNE Sabadell.
     */
    private static String passwordReuneSabadell;

    /**
     * Correo de remitente para el envío de correos.
     */
    private static String correoRemitente;

    /**
     * Host del servidor SMTP.
     */
    private static String smtpHost;

    /**
     * Número de intentos para el envío del correo.
     */
    private static int intentosEnvioCorreo;
    
    /**
     * Directorio para reintentos 
     */
    private static String dirNasReintentos;
    
    /**
     * Logger general de Condusef.
     */
	private static final Logger logger = LoggerFactory.getLogger("condusefLogger");

    
    static {
        logger.info("Carga de archivo de properties.");
    	Properties prop = new Properties();

        try {
        	// Obtener el nomnbre de archivo de configuración.
        	InputStream inputStream  = new FileInputStream("./config.properties");
            // Lectura del archivo.
        	prop.load(inputStream);
        } catch(FileNotFoundException ex){
        	// Muestra el error generado
        	logger.error(ex.getMessage());
        	for (StackTraceElement trace: ex.getStackTrace()) {
        		logger.error(trace.toString());
        	}
            throw new IllegalStateException("Error al cargar el archivo de configuración", ex);
        } catch (IOException e) {
        	// Muestra el error generado
        	logger.error(e.getMessage());
        	for (StackTraceElement trace: e.getStackTrace()) {
        		logger.error(trace.toString());
        	}
        	throw new IllegalStateException("Error al cargar el archivo de configuración", e);
		}
        logger.info("Carga de propiedades");        
        ConfigConstants.setDirNas(prop.getProperty("mx.bancosabadel.dir_nas.url.directories.base"));
        ConfigConstants.setDirNasLog(prop.getProperty("mx.bancosabadel.dir_nas.url.directories.url_logs"));
        ConfigConstants.setDirNasRedeco(prop.getProperty("mx.bancosabadel.dir_nas.url.directories.url_redeco"));
        ConfigConstants.setDirNasReune(prop.getProperty("mx.bancosabadel.dir_nas.url.directories.url_reune"));
        ConfigConstants.setDirNasHistorico(prop.getProperty("mx.bancosabadel.dir_nas.url.directories.url_historico"));
        ConfigConstants.setDirNasTicket(prop.getProperty("mx.bancosabadel.dir_nas.url.directories.url_tiket"));
        ConfigConstants.setDirNasReintentos(prop.getProperty("mx.bancosabadel.dir_nas.url.directories.url_reintentos"));

        ConfigConstants.setCorreoNotificaRedeco(prop.getProperty("mx.bancosabadel.conducef.email.notif_redeco"));
        ConfigConstants.setCorreoNotificaReune(prop.getProperty("mx.bancosabadel.conducef.email.notif_reune"));
        
        ConfigConstants.setUrlServMail(prop.getProperty("mx.bancosabadel.latinia.url.base"));

        ConfigConstants.setUrlBase(prop.getProperty("mx.bancosabadel.conducef.url.base"));
        ConfigConstants.setUrlApiReune(prop.getProperty("mx.bancosabadel.conducef.url.api_reune.path"));
        ConfigConstants.setUrlApiRedeco(prop.getProperty("mx.bancosabadel.conducef.url.api_redeco.path"));

        ConfigConstants.setUrlTokenRedeco(prop.getProperty("mx.bancosabadel.conducef.url.api_redeco.token"));
        ConfigConstants.setPathTokenRedeco(prop.getProperty("mx.bancosabadel.conducef.url.api_redeco.token.path"));
        
        ConfigConstants.setUrlTokenReune(prop.getProperty("mx.bancosabadel.conducef.url.api_reune.token"));
        ConfigConstants.setPathTokenReune(prop.getProperty("mx.bancosabadel.conducef.url.api_reune.token.path"));

        ConfigConstants.setTokenCondusef(prop.getProperty("mx.bancosabadel.conducef.token"));
        ConfigConstants.setRegExpQuejasRedecoSabadell(prop.getProperty("mx.bancosabadel.conducef.file.sabadell.reg_exp_quejas_redeco"));
        ConfigConstants.setRegExpQuejasReuneSabadell(prop.getProperty("mx.bancosabadel.conducef.file.sabadell.reg_exp_quejas_reune"));
        
        ConfigConstants.setRegExpQuejasRedecoSofom(prop.getProperty("mx.bancosabadel.conducef.file.sofom.reg_exp_quejas_redeco"));
        ConfigConstants.setRegExpQuejasReuneSofom(prop.getProperty("mx.bancosabadel.conducef.file.sofom.reg_exp_quejas_reune"));

        ConfigConstants.setUserNameRedecoSabadell(prop.getProperty("mx.bancosabadel.conducef.url.api_redeco.sabadell.username"));
        ConfigConstants.setPasswordRedecoSabadell(prop.getProperty("mx.bancosabadel.conducef.url.api_redeco.sabadell.password"));

        ConfigConstants.setUserNameRedecoSofom(prop.getProperty("mx.bancosabadel.conducef.url.api_redeco.sofom.username"));
        ConfigConstants.setPasswordRedecoSofom(prop.getProperty("mx.bancosabadel.conducef.url.api_redeco.sofom.password"));
        
        ConfigConstants.setUserNameReuneSabadell(prop.getProperty("mx.bancosabadel.conducef.url.api_reune.sabadell.username"));
        ConfigConstants.setPasswordReuneSabadell(prop.getProperty("mx.bancosabadel.conducef.url.api_reune.sabadell.password"));
        
        ConfigConstants.setUserNameReuneSofom(prop.getProperty("mx.bancosabadel.conducef.url.api_reune.sofom.username"));
        ConfigConstants.setPasswordReuneSofom(prop.getProperty("mx.bancosabadel.conducef.url.api_reune.sofom.password"));

        ConfigConstants.setCorreoRemitente(prop.getProperty("mx.bancosabadel.conducef.email.remitente"));
        ConfigConstants.setSmtpHost(prop.getProperty("mx.bancosabadel.conducef.email.smtp_host"));
        String intentos = prop.getProperty("mx.bancosabadel.conducef.email.intentos");
        ConfigConstants.setIntentosEnvioCorreo(Integer.parseInt(intentos));
    }
    
    /**
     * Obtiene el directorio NAS.
     * @return Directorio NAS.
     */
	public static String getDirNas() {
		return dirNas;
	}

	/**
	 * Define el directorio NAS.
	 * @param dirNas Directorio NAS.
	 */
	public static void setDirNas(String dirNas) {
		ConfigConstants.dirNas = dirNas;
	}

	/**
	 * Obtiene el directorio de logs.
	 * @return directorio de logs.
	 */
	public static String getDirNasLog() {
		return dirNasLog;
	}

	/**
	 * Define el directorio de logs.
	 * @param dirNasLog Directorio de logs.
	 */
	public static void setDirNasLog(String dirNasLog) {
		ConfigConstants.dirNasLog = dirNasLog;
	}

	/**
	 * Obtiene el directorio de REDECO.
	 * @return Directorio de REDECO.s
	 */
	public static String getDirNasRedeco() {
		return dirNasRedeco;
	}

	/**
	 * Define el directorio de REDECO.
	 * @param dirNasRedeco Directorio de REDECO.
	 */
	public static void setDirNasRedeco(String dirNasRedeco) {
		ConfigConstants.dirNasRedeco = dirNasRedeco;
	}

	/**
	 * Obtiene el directorio de REUNE.
	 * @return Directorio de REUNE
	 */
	public static String getDirNasReune() {
		return dirNasReune;
	}

	/**
	 * Define el directorio de REUNE
	 * @param dirNasReune Directorio de REUNE
	 */
	public static void setDirNasReune(String dirNasReune) {
		ConfigConstants.dirNasReune = dirNasReune;
	}

	/**
	 * Define el directorio histórico.
	 * @return Directorio histórico.
	 */
	public static String getDirNasHistorico() {
		return dirNasHistorico;
	}

	/**
	 * Define el directorio histórico.
	 * @param dirNasHistorico Directorio histórico.
	 */
	public static void setDirNasHistorico(String dirNasHistorico) {
		ConfigConstants.dirNasHistorico = dirNasHistorico;
	}

	/**
	 * Define el directorio para el guardado de tickets.
	 * @return Directorio para el guardado de tickets.
	 */
	public static String getDirNasTicket() {
		return dirNasTicket;
	}

	/**
	 * Define el directorio para el guardado de tickets.
	 * @param dirNasTicket Directorio para el guardado de tickets.
	 */
	public static void setDirNasTicket(String dirNasTicket) {
		ConfigConstants.dirNasTicket = dirNasTicket;
	}

	/**
	 * Obtiene el correo de notificaciones para REDECO.
	 * @return Correo de notificaciones para REDECO.
	 */
	public static String getCorreoNotificaRedeco() {
		return correoNotificaRedeco;
	}

	/**
	 * Defin el correo de notificaciones para REDECO.
	 * @param correoNotifcaRedeco Correo de notificaciones para REDECO.
	 */
	public static void setCorreoNotificaRedeco(String correoNotifcaRedeco) {
		ConfigConstants.correoNotificaRedeco = correoNotifcaRedeco;
	}

	/**
	 * Obtiene el correo de notificaciones para REUNE.
	 * @return Correo de notificaciones para REDECO.
	 */
	public static String getCorreoNotificaReune() {
		return correoNotificaReune;
	}

	/**
	 * Define correo de notificaciones para REUNE.
	 * @param correoNotificaReune Correo de notificaciones para REDECO.
	 */
	public static void setCorreoNotificaReune(String correoNotificaReune) {
		ConfigConstants.correoNotificaReune = correoNotificaReune;
	}

	/**
	 * Obtiene la URL del servicio de correo.
	 * @return URL del servicio de correo.
	 */
	public static String getUrlServMail() {
		return urlServMail;
	}

	/**
	 * Define el URL del servicio de correo.
	 * @param urlServMail URL del servicio de correo.
	 */
	public static void setUrlServMail(String urlServMail) {
		ConfigConstants.urlServMail = urlServMail;
	}

	/**
	 * Obtiene la URL base.
	 * @return URL base.
	 */
	public static String getUrlBase() {
		return urlBase;
	}

	/**
	 * Define la URL base.
	 * @param urlBase URL base.
	 */
	public static void setUrlBase(String urlBase) {
		ConfigConstants.urlBase = urlBase;
	}

	/**
	 * Obtiene la URL de la API REUNE.
	 * @return URL de la API REUNE.
	 */
	public static String getUrlApiReune() {
		return urlApiReune;
	}

	/**
	 * Define la URL de la API REUNE.
	 * @param urlApiReune URL de la API REUNE.
	 */
	public static void setUrlApiReune(String urlApiReune) {
		ConfigConstants.urlApiReune = urlApiReune;
	}

	/**
	 * Obtiene la URL de la API REDECO.
	 * @return URL de la API REDECO.
	 */
	public static String getUrlApiRedeco() {
		return urlApiRedeco;
	}

	/**
	 * Define la URL de la API REDECO.
	 * @param urlApiRedeco URL de la API REDECO.
	 */
	public static void setUrlApiRedeco(String urlApiRedeco) {
		ConfigConstants.urlApiRedeco = urlApiRedeco;
	}

	/**
	 * Obtiene la URL para el token de REDECO.
	 * @return URL para el token de REDECO.
	 */
	public static String getUrlTokenRedeco() {
		return urlTokenRedeco;
	}

	/**
	 * Define la URL para el token de REDECO.
	 * @param urlTokenRedeco URL para el token de REDECO.
	 */
	public static void setUrlTokenRedeco(String urlTokenRedeco) {
		ConfigConstants.urlTokenRedeco = urlTokenRedeco;
	}

	/**
	 * Obtiene la ruta para el token de REDECO.
	 * @return ruta para el token de REDECO.
	 */
	public static String getPathTokenRedeco() {
		return pathTokenRedeco;
	}

	/**
	 * Define la ruta para el token de REDECO.
	 * @param pathTokenRedeco Ruta para el token de REDECO.
	 */
	public static void setPathTokenRedeco(String pathTokenRedeco) {
		ConfigConstants.pathTokenRedeco = pathTokenRedeco;
	}

	/**
	 * Obtiene la URL del Token de REUNE.
	 * @return URL del Token de REUNE.
	 */
	public static String getUrlTokenReune() {
		return urlTokenReune;
	}

	/**
	 * Define la URL del Token de REUNE.
	 * @param urlTokenReune URL del Token de REUNE.
	 */
	public static void setUrlTokenReune(String urlTokenReune) {
		ConfigConstants.urlTokenReune = urlTokenReune;
	}

	/**
	 * Obtiene la ruta del token de REUNE.
	 * @return Ruta del token de REUNE.
	 */
	public static String getPathTokenReune() {
		return pathTokenReune;
	}

	/**
	 * Define la ruta del token de REUNE.
	 * @param pathTokenReune Ruta del token de REUNE.
	 */
	public static void setPathTokenReune(String pathTokenReune) {
		ConfigConstants.pathTokenReune = pathTokenReune;
	}

	/**
	 * Obtiene el token de Condusef.
	 * @return Token de Condusef.
	 */
	public static String getTokenCondusef() {
		return tokenCondusef;
	}

	/**
	 * Define el token de Condusef.
	 * @param tokenCondusef Token de Condusef.
	 */
	public static void setTokenCondusef(String tokenCondusef) {
		ConfigConstants.tokenCondusef = tokenCondusef;
	}

	/**
	 * Obtiene la ruta del archivo d configuración.
	 * @return Ruta del archivo d configuración.
	 */
	public static String getUrlConfigFile() {
		return urlConfigFile;
	}

	/**
	 * Define ruta del archivo d configuración.
	 * @param urlConfigFile Ruta del archivo d configuración.
	 */
	public static void setUrlConfigFile(String urlConfigFile) {
		ConfigConstants.urlConfigFile = urlConfigFile;
	}

	/**
	 * Obtiene el parámetro de autorización en el header.
	 * @return Parámetro de autorización en el header.
	 */
	public static String getHeaderAuth() {
		return headerAuth;
	}

	/**
	 * Define parámetro de autorización en el header.
	 * @param headerAuth Parámetro de autorización en el header.
	 */
	public static void setHeaderAuth(String headerAuth) {
		ConfigConstants.headerAuth = headerAuth;
	}

	/**
	 * Obtiene la expresión regular para el nombre de archivo de REDECO Sabadell.
	 * @return Expresión regular para el nombre de archivo de REDECO Sabadell.
	 */
	public static String getRegExpQuejasRedecoSabadell() {
		return regExpQuejasRedecoSabadell;
	}

	/**
	 * Define la expresión regular para el nombre de archivo de REDECO Sabadell.
	 * @param regExpQuejasRedecoSabadell Expresión regular para el nombre de archivo de REDECO Sabadell.
	 */
	public static void setRegExpQuejasRedecoSabadell(String regExpQuejasRedecoSabadell) {
		ConfigConstants.regExpQuejasRedecoSabadell = regExpQuejasRedecoSabadell;
	}

	/**
	 * Obtiene la expresión regular para el nombre de archivo de REDECO Sofom.
	 * @return Expresión regular para el nombre de archivo de REDECO Sofom.
	 */
	public static String getRegExpQuejasRedecoSofom() {
		return regExpQuejasRedecoSofom;
	}

	/**
	 * Define la expresión regular para el nombre de archivo de REDECO Sofom.
	 * @param regExpQuejasRedecoSofom Expresión regular para el nombre de archivo de REDECO Sofom.
	 */
	public static void setRegExpQuejasRedecoSofom(String regExpQuejasRedecoSofom) {
		ConfigConstants.regExpQuejasRedecoSofom = regExpQuejasRedecoSofom;
	}

	/**
	 * Obtiene la expresión regular para el nombre de archivo de REUNE Sabadell.
	 * @return Expresión regular para el nombre de archivo de REUNE Sabadell.
	 */
	public static String getRegExpQuejasReuneSabadell() {
		return regExpQuejasReuneSabadell;
	}

	/**
	 * Define la expresión regular para el nombre de archivo de REUNE Sabadell.
	 * @param regExpQuejasReuneSabadell Expresión regular para el nombre de archivo de REUNE Sabadell.
	 */
	public static void setRegExpQuejasReuneSabadell(String regExpQuejasReuneSabadell) {
		ConfigConstants.regExpQuejasReuneSabadell = regExpQuejasReuneSabadell;
	}

	/**
	 * Obtiene la expresión regular para el nombre de archivo de REUNE Sofom.
	 * @return Expresión regular para el nombre de archivo de REUNE Sofom.
	 */
	public static String getRegExpQuejasReuneSofom() {
		return regExpQuejasReuneSofom;
	}

	/**
	 * Define la expresión regular para el nombre de archivo de REUNE Sofom.
	 * @param regExpQuejasReuneSofom Expresión regular para el nombre de archivo de REUNE Sofom.
	 */
	public static void setRegExpQuejasReuneSofom(String regExpQuejasReuneSofom) {
		ConfigConstants.regExpQuejasReuneSofom = regExpQuejasReuneSofom;
	}

	/**
	 * Define el nombre de usuario para REDECO Sabadell.
	 * @return Nombre de usuario para REDECO Sabadell.
	 */
	public static String getUserNameRedecoSabadell() {
		return userNameRedecoSabadell;
	}

	/**
	 * Define el nombre de usuario para REDECO Sabadell.
	 * @param userNameRedeco Nombre de usuario para REDECO Sabadell.
	 */
	public static void setUserNameRedecoSabadell(String userNameRedecoSabadell) {
		ConfigConstants.userNameRedecoSabadell = userNameRedecoSabadell;
	}

	/**
	 * Define el nombre de usuario para REDECO Sofom.
	 * @return Nombre de usuario para REDECO Sofom.
	 */
	public static String getUserNameRedecoSofom() {
		return userNameRedecoSofom;
	}

	/**
	 * Define el nombre de usuario para REDECO Sofom.
	 * @param userNameRedeco Nombre de usuario para REDECO Sofom.
	 */
	public static void setUserNameRedecoSofom(String userNameRedecoSofom) {
		ConfigConstants.userNameRedecoSofom = userNameRedecoSofom;
	}

	/**
	 * Obtiene la contraseña para el usuario de REDECO Sabadell.
	 * @return Contraseña para el usuario de REDECO Sabadell.
	 */
	public static String getPasswordRedecoSabadell() {
		return passwordRedecoSabadell;
	}

	/**
	 * Define el contraseña para el usuario de REDECO Sabadell.
	 * @param passwordRedeco Contraseña para el usuario de REDECO Sabadell.
	 */
	public static void setPasswordRedecoSabadell(String passwordRedecoSabadell) {
		ConfigConstants.passwordRedecoSabadell = passwordRedecoSabadell;
	}

	/**
	 * Obtiene la contraseña para el usuario de REDECO Sofom.
	 * @return Contraseña para el usuario de REDECO Sofom.
	 */
	public static String getPasswordRedecoSofom() {
		return passwordRedecoSofom;
	}

	/**
	 * Define el contraseña para el usuario de REDECO.
	 * @param passwordRedeco Contraseña para el usuario de REDECO.
	 */
	public static void setPasswordRedecoSofom(String passwordRedecoSofom) {
		ConfigConstants.passwordRedecoSofom = passwordRedecoSofom;
	}

	/**
	 * Obtiene el nombre de usuario de REUNE Sabadell.
	 * @return Nombre de usuario de REUNE Sabadell.
	 */
	public static String getUserNameReuneSabadell() {
		return userNameReuneSabadell;
	}

	/**
	 * Define el nombre de usuario de REUNE Sabadell.
	 * @param userNameReune Nombre de usuario de REUNE Sabadell.
	 */
	public static void setUserNameReuneSabadell(String userNameReuneSabadell) {
		ConfigConstants.userNameReuneSabadell = userNameReuneSabadell;
	}

	/**
	 * Obtiene el nombre de usuario de REUNE Sofom.
	 * @return Nombre de usuario de REUNE Sofom.
	 */
	public static String getUserNameReuneSofom() {
		return userNameReuneSofom;
	}

	/**
	 * Define el nombre de usuario de REUNE Sofom.
	 * @param userNameReune Nombre de usuario de REUNE Sofom.
	 */
	public static void setUserNameReuneSofom(String userNameReuneSofom) {
		ConfigConstants.userNameReuneSabadell = userNameReuneSofom;
	}

	/**
	 * Obtiene la contraseña del usuario de REUNE Sabadell.
	 * @return Contraseña del usuario de REUNE Sabadell.
	 */
	public static String getPasswordReuneSabadell() {
		return passwordReuneSabadell;
	}

	/**
	 * Define la contraseña del usuario de REUNE Sabadell.
	 * @param passwordReune Contraseña del usuario de REUNE Sabadell.
	 */
	public static void setPasswordReuneSabadell(String passwordReuneSabadell) {
		ConfigConstants.passwordReuneSabadell = passwordReuneSabadell;
	}

	/**
	 * Obtiene la contraseña del usuario de REUNE Sofom.
	 * @return Contraseña del usuario de REUNE Sofom.
	 */
	public static String getPasswordReuneSofom() {
		return passwordReuneSofom;
	}

	/**
	 * Define la contraseña del usuario de REUNE Sofom.
	 * @param passwordReune Contraseña del usuario de REUNE Sofom.
	 */
	public static void setPasswordReuneSofom(String passwordReuneSofom) {
		ConfigConstants.passwordReuneSofom = passwordReuneSofom;
	}

	/**
     * Obtiene el correo del remitente para el envío de correos.
     * @return Correo de remitente;
     */
	public static String getCorreoRemitente() {
		return correoRemitente;
	}

	/**
	 * Define el correo del remitente para el envío de correos.
	 * @param correoRemitente Correo del remitente para el envío de correos.
	 */
	public static void setCorreoRemitente(String correoRemitente) {
		ConfigConstants.correoRemitente = correoRemitente;
	}

	/**
	 * Obtiene el host del servidor SMTP para el envío de correo.
	 * @return Host del servidor SMTP para el envío de correo
	 */
	public static String getSmtpHost() {
		return smtpHost;
	}
	/**
	 * Define el host del servidor SMTP para el envío de correo
	 * @param smtpHost Host del servidor SMTP para el envío de correo
	 */
	public static void setSmtpHost(String smtpHost) {
		ConfigConstants.smtpHost = smtpHost;
	}

	/**
	 * Obtiene el número de intentos para el envío de correos.
	 * @return Número de intentos para el envío de correos.
	 */
	public static int getIntentosEnvioCorreo() {
		return intentosEnvioCorreo;
	}

	/**
	 * Define el número de intentos para el envío de correos.
	 * @param intentosEnvioCorreo Número de intentos para el envío de correos.
	 */
	public static void setIntentosEnvioCorreo(int intentosEnvioCorreo) {
		ConfigConstants.intentosEnvioCorreo = intentosEnvioCorreo;
	}
	
	/**
	 * Obtiene el directorio para reintentos.
	 * @return Directorio para reintentos.
	 */
	public static String getDirNasReintentos() {
		return dirNasReintentos;
	}

	/**
	 * Define el directorio para reintentos.
	 * @param dirNasReintentos Directorio para reintentos.
	 */
	public static void setDirNasReintentos(String dirNasReintentos) {
		ConfigConstants.dirNasReintentos = dirNasReintentos;
	}

}
