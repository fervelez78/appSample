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

public class Monitor {

    public String rutaArchivo = ConfigConstants.DIR_NAS_TIKETS;

    public String urlNas = ConfigConstants.DIR_NAS;

    public String pathRedeco = ConfigConstants.DIR_NAS_REDECO;
    public String pathReune = ConfigConstants.DIR_NAS_REUNE;
    
    public String urlNasLog = ConfigConstants.DIR_NAS_LOGS;
    
    public String urlHistorico = ConfigConstants.DIR_NAS_HISTORICO;

    
    private static final Logger loggerRedeco = LoggerFactory.getLogger("clientRedecoLogger");
    private static final Logger loggerReune = LoggerFactory.getLogger("clientReuneLogger");
    
    public void createState(String bolean){

        FileWriter archivo = null;
        PrintWriter escritor = null;

        try {
            archivo = new FileWriter(rutaArchivo + "EstadoDeEnvio.txt");
            escritor = new PrintWriter(archivo);

            // Aquí se escribe en el archivo
            escritor.println(bolean);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (archivo != null)
                    archivo.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }    
    };
    
    public String readState(){

        String state = null;

         try {
            FileReader archivo = new FileReader(rutaArchivo+"EstadoDeEnvio.txt");
            BufferedReader lector = new BufferedReader(archivo);

            String linea;
            while ((linea = lector.readLine()) != null) {
                state = linea;
            }

            lector.close(); // Cierra el BufferedReader

        } catch (IOException e) {
            e.printStackTrace();
        }
        return state;
    }

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
    
        FileFilter archivosFilter = new FileFilter() {
            //Sobreescribimos el método
            public boolean accept(File file) {
                // Nombre del archivo los nombres aceptados son: 
                // REDECO_QUEJAS.xlsx, REDECO_QUEJAS_[YYYYMMDD].xlsx, REDECO_QUEJAS_[YYYYMMDDHHMM].xlsx
                String nombreArchivoEsperado = ConfigConstants.REG_EXP_QUEJAS_REDECO; 
                
    
                if (file.getName().matches(nombreArchivoEsperado)) {
                    return true;
                }
                return false;
            }
        };
     
        
}

