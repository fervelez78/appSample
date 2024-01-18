package mx.bancosabadell.condusef.services;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mx.bancosabadell.condusef.config.ConfigConstants;

/* Por definir funcionalidad */
public class Monitor {



    private static final Logger logger = LoggerFactory.getLogger(Monitor.class);
    
    public String urlNas = ConfigConstants.DIR_NAS;
    public String urlNasLog = ConfigConstants.DIR_NAS_LOGS;
    public String urlHistorico = ConfigConstants.DIR_NAS_HISTORICO;

   

    public void searchFiles(){
       logger.info("INICIO searchFiles Monitor");

       logger.info("FIN searchFiles Monitor");
    }

    public void uploadFile(){}

    public void moveFiles(){}

    public void sendEmail(){}
}