package mx.bancosabadell.condusef.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import mx.bancosabadell.condusef.Condusef;

public class ConfigConstants {

    public static String DIR_NAS;
    public static String DIR_NAS_LOGS;
    public static String DIR_NAS_REDECO;
    public static String DIR_NAS_REUNE;
    public static String DIR_NAS_HISTORICO;
    public static String DIR_NAS_TIKETS;

    
    public static String CORREO_NOTIF_REDECO;
    public static String CORREO_NOTIF_REUNE;
    public static String URL_SERV_EMAIL;
    
    public static String URL_BASE;
    public static String URL_API_REUNE;
    public static String URL_API_REDECO;

    public static String TOKEN_CONDUSEF;

    public static String URL_CONFIG_FILE = "src\\main\\resources\\config.properties";

    public static String HEADER_AUTH = "Authorization";

    public static String REG_EXP_QUEJAS_REDECO;
    public static String REG_EXP_QUEJAS_REUNE;


    static {
        Properties prop = new Properties();
        //Properties properties = new Properties();

        /* InputStream input = new FileInputStream(URL_CONFIG_FILE) */                  
        try {
        	// InputStream input = ConfigConstants.class.getClassLoader().getResourceAsStream("C:\\Indra\\rep_condusef\\bin\\config.properties")) {
        	
            //ClassLoader loader = Condusef.class.getClassLoader();
            

        	//properties.load(loader.getResourceAsStream("configSystem.properties"));
        	//prop.load(loader.getResourceAsStream("config.properties"));
        	//InputStream inputStream = ConfigConstants.class.getClassLoader().getResourceAsStream("C:/Indra/rep_condusef/bin/config.properties");
        	InputStream inputStream  = new FileInputStream("./config.properties");
            
        	prop.load(inputStream);
        } catch(FileNotFoundException ex){
        	ex.printStackTrace();
            throw new IllegalStateException("Error al cargar el archivo de configuración", ex);
        } catch (IOException e) {
        	e.printStackTrace();
			// TODO Auto-generated catch block
        	throw new IllegalStateException("Error al cargar el archivo de configuración", e);
		}
        
        
        DIR_NAS = prop.getProperty("mx.bancosabadel.dir_nas.url.directories.base");
        DIR_NAS_LOGS = prop.getProperty("mx.bancosabadel.dir_nas.url.directories.url_logs");
        DIR_NAS_REDECO = prop.getProperty("mx.bancosabadel.dir_nas.url.directories.url_redeco");
        DIR_NAS_REUNE = prop.getProperty("mx.bancosabadel.dir_nas.url.directories.url_reune");
        DIR_NAS_HISTORICO = prop.getProperty("mx.bancosabadel.dir_nas.url.directories.url_historico");
        DIR_NAS_TIKETS = prop.getProperty("mx.bancosabadel.dir_nas.url.directories.url_tiket");

        CORREO_NOTIF_REDECO = prop.getProperty("mx.bancosabadel.conducef.email_notif_redeco");
        CORREO_NOTIF_REUNE = prop.getProperty("mx.bancosabadel.conducef.email_notif_reune");
        URL_SERV_EMAIL = prop.getProperty("mx.bancosabadel.latinia.url.base");

        URL_BASE = prop.getProperty("mx.bancosabadel.conducef.url.base");
        URL_API_REUNE = prop.getProperty("mx.bancosabadel.conducef.url.api_reune.path");
        URL_API_REDECO = prop.getProperty("mx.bancosabadel.conducef.url.api_redeco.path");
        TOKEN_CONDUSEF = prop.getProperty("mx.bancosabadel.conducef.token");
        REG_EXP_QUEJAS_REDECO = prop.getProperty("mx.bancosabadel.conducef.file.reg_exp_quejas_redeco");
        REG_EXP_QUEJAS_REUNE = prop.getProperty("mx.bancosabadel.conducef.file.reg_exp_quejas_reune");
        
    }
}
