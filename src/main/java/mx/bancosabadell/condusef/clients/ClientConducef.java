package mx.bancosabadell.condusef.clients;

import java.io.File;

import org.apache.http.HttpResponse;

public abstract class ClientConducef {    
    
    public abstract HttpResponse uploadFile(File file, String ulr_service);
   
}

