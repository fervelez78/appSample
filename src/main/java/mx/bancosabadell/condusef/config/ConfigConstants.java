package mx.bancosabadell.condusef.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigConstants {

    public static String DIR_NAS;
    public static String DIR_NAS_LOGS;
    public static String DIR_NAS_REDECO;
    public static String DIR_NAS_REUNE;
    public static String DIR_NAS_HISTORICO;

    
    public static String CORREO_NOTIF_REDECO;
    public static String URL_SERV_EMAIL;
    
    public static String URL_BASE;
    public static String URL_API_REUNE;
    public static String URL_API_REDECO;

    public static String TOKEN_CONDUSEF;

    public static String URL_CONFIG_FILE = "src\\main\\resources\\config.properties";


    static {
        Properties prop = new Properties();

        /* InputStream input = new FileInputStream(URL_CONFIG_FILE) */                  
        try (   InputStream input = ConfigConstants.class.getClassLoader().getResourceAsStream("config.properties")) {
            prop.load(input);
        } catch(IOException ex){
            throw new IllegalStateException("Error al cargar el archivo de configuración", ex);
        }
            
        DIR_NAS = prop.getProperty("mx.bancosabadel.dir_nas.url.directories.base");
        DIR_NAS_LOGS = prop.getProperty("mx.bancosabadel.dir_nas.url.directories.url_logs");
        DIR_NAS_REDECO = prop.getProperty("mx.bancosabadel.dir_nas.url.directories.url_redeco");
        DIR_NAS_REUNE = prop.getProperty("mx.bancosabadel.dir_nas.url.directories.url_reune");
        DIR_NAS_HISTORICO = prop.getProperty("mx.bancosabadel.dir_nas.url.directories.url_historico");

        CORREO_NOTIF_REDECO = prop.getProperty("mx.bancosabadel.conducef.email_notif_redeco");
        URL_SERV_EMAIL = prop.getProperty("mx.bancosabadel.latinia.url.base");

        URL_BASE = prop.getProperty("mx.bancosabadel.latinia.url.base");
        URL_API_REUNE = prop.getProperty("mx.bancosabadel.conducef.url.api_reune.path");
        URL_API_REDECO = prop.getProperty("mx.bancosabadel.conducef.url.api_redeco.path");
        TOKEN_CONDUSEF = prop.getProperty("mx.bancosabadel.conducef.token");
        
    }
}
