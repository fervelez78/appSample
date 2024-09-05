package mx.bancosabadell.condusef.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mx.bancosabadell.condusef.config.ConfigConstants;

/**
 * Clase de monitoreo del proceso.
 */
public class Monitor {

	/**
	 * Ruta del archivo de ticket.
	 */
    public String rutaArchivo = ConfigConstants.getDirNasTicket();

    /**
     * Dirección donde se encuentra el directorio NAS.
     */
    public String urlNas = ConfigConstants.getDirNas();

    /**
     * Ruta del directorio de REDECO.
     */
    public String pathRedeco = ConfigConstants.getDirNasRedeco();
    
    /**
     * Ruta del directorio de REUNE.
     */
    public String pathReune = ConfigConstants.getDirNasReune();
    
    /**
     * Ruta del directorio donde se guardan los logs.
     */
    public String urlNasLog = ConfigConstants.getDirNasLog();
    
    /**
     * Directorio donde se guardarán los archivos procesados.
     */
    public String urlHistorico = ConfigConstants.getDirNasHistorico();
    
    /**
     * Razon social que ejecuta el proceso
     */
    private String razonSocial;

    /**
     * Recupera la razon social que ejecuta el proceso
     * @return Razon social que ejecuta el proceso
     */
    public String getRazonSocial() {
		return razonSocial;
	}

    /**
     * Define la razon social que ejecuta el proceso
     * @param razonSocial Razon social que ejecuta el proceso
     */
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	/**
     * Logger general de Condusef.
     */
	private static final Logger logger = LoggerFactory.getLogger("condusefLogger");
    
	/**
	 * Logger de redeco.
	 */
    private static final Logger loggerRedeco = LoggerFactory.getLogger("clientRedecoLogger");
    
    /**
     * Logger de REUNE.
     */
    private static final Logger loggerReune = LoggerFactory.getLogger("clientReuneLogger");
    
    public Monitor(String razonSocial) {
    	setRazonSocial(razonSocial);
    }
    
    /**
     * Método para crear un estado del envío de archivos.
     * @param bolean Valida si se ha procesado.
     */ 
    public void createState(String bolean){

        FileWriter archivo = null;
        PrintWriter escritor = null;

        try {
            archivo = new FileWriter(rutaArchivo + "EstadoDeEnvio" + razonSocial + ".txt");
            escritor = new PrintWriter(archivo);

            // Aquí se escribe en el archivo
            escritor.println(bolean);

        } catch (IOException e) {
        	logger.error(e.getMessage());
			for (StackTraceElement trace: e.getStackTrace()) {
        		logger.error(trace.toString());
        	}      
			
        } finally {
            try {
                if (archivo != null)
                    archivo.close();
            } catch (IOException e) {
            	logger.error(e.getMessage());
    			for (StackTraceElement trace: e.getStackTrace()) {
            		logger.error(trace.toString());
            	}      
            }
        }    
    };
    
    /**
     * Lectura del estatus.
     * @return Estatus.
     */
    public String readState(){

        String state = null;

         try {
            FileReader archivo = new FileReader(rutaArchivo+"EstadoDeEnvio" + razonSocial + ".txt");
            BufferedReader lector = new BufferedReader(archivo);

            String linea;
            while ((linea = lector.readLine()) != null) {
                state = linea;
            }

            lector.close(); // Cierra el BufferedReader

        } catch (IOException e) {
        	logger.error(e.getMessage());
			for (StackTraceElement trace: e.getStackTrace()) {
        		logger.error(trace.toString());
        	}      
        }
        return state;
    }

    /**
     * Lectura de los directorios.
     * @return archivos encontrados.
     * @throws Exception Error al momento de buscar los archivos.
     */
    public List<String> readFolders() throws Exception{
       
            // Directorio donde se copian los archivos de Redeco
        File directorioRedeco = new File(urlNas + pathRedeco);

        if (!directorioRedeco.isDirectory()) {
            String error = "La ruta especificada para Redeco no es un directorio válido. " + urlNas + pathRedeco;
            loggerRedeco.error(error);
            throw new Exception(error);
        }

        // Directorio donde se copian los archivos de Reune
        File directorioReune = new File(urlNas + pathReune);

        if (!directorioReune.isDirectory()) {
            String error = "La ruta especificada para Reune no es un directorio válido. " + urlNas + pathReune;
            loggerReune.error(error);
            throw new Exception(error);
        }

        List<String> nombresArchivos = new ArrayList<>();

        // Buscar archivos en la carpeta de Redeco
        File[] archivosRedeco = directorioRedeco.listFiles(archivosFilter);
        if (archivosRedeco != null && archivosRedeco.length > 0) {
            for (File archivo : archivosRedeco) {
                String nombreArchivoRedeco = archivo.getName();
                loggerRedeco.info("Archivo encontrado en Redeco: " + nombreArchivoRedeco);
                nombresArchivos.add(nombreArchivoRedeco);
            }
        }

        // Buscar archivos en la carpeta de Reune
        File[] archivosReune = directorioReune.listFiles(archivosFilter);
        if (archivosReune != null && archivosReune.length > 0) {
            for (File archivo : archivosReune) {
                String nombreArchivoReune = archivo.getName();
                loggerReune.info("Archivo encontrado en Reune: " + nombreArchivoReune);
                nombresArchivos.add(nombreArchivoReune);
            }
        }

        // Si no se encontró ningún archivo en ninguna de las carpetas
        if (nombresArchivos.isEmpty()) {
            String mensajeError = "Sin archivos para condusef";
            nombresArchivos.add(mensajeError);
            loggerRedeco.error(mensajeError);
            loggerReune.error(mensajeError);
        }

        return nombresArchivos;
    }
    
    /**
     * Filtro utilzado para la búsqueda de arhivos de REDECO.
     */
    FileFilter archivosFilter = new FileFilter() {
        //Sobreescribimos el método
        public boolean accept(File file) {
            // Nombre del archivo los nombres aceptados son: 
            // REDECO_QUEJAS.xlsx, REDECO_QUEJAS_[YYYYMMDD].xlsx, REDECO_QUEJAS_[YYYYMMDDHHMM].xlsx
        	String nombreArchivoEsperado = null;
        	
        	if (razonSocial.trim().toUpperCase().equals("SABADELL"))
        		nombreArchivoEsperado = ConfigConstants.getRegExpQuejasRedecoSabadell(); 
        	if (razonSocial.trim().toUpperCase().equals("SOFOM"))
        		nombreArchivoEsperado = ConfigConstants.getRegExpQuejasRedecoSofom(); 
            

            if (nombreArchivoEsperado != null && file.getName().matches(nombreArchivoEsperado)) {
                return true;
            }
            return false;
        }
    };
     
        
}

