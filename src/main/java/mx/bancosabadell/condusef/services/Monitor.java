package mx.bancosabadell.condusef.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import mx.bancosabadell.condusef.config.ConfigConstants;

public class Monitor {

    public String rutaArchivo = ConfigConstants.DIR_NAS_TIKETS;

    

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

     
        
}

