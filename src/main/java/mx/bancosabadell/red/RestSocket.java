package mx.bancosabadell.red;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.SSLSocket;

public class RestSocket {

	Socket socket;
	
	int codeResult;
	
	String msgResult;
	
	String body;
	
	String host;
	
	HashMap<String, String> properties;
	
	public Socket getSocket() {
		return this.socket;
	}
	
	public int getCodeResult() {
		return this.codeResult;
	}
	
	public String getMsgResult() {
		return this.msgResult;
	}
	
	public String getBody() {
		return this.body;
	}
	
	public RestSocket() {
		properties = new HashMap<String, String>();
	}
	
	public void buildProxySSl(String host, String proxyHost, int proxyPort) throws UnknownHostException, IOException {
		Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyHost, proxyPort));
        Socket proxySocket = new Socket(proxy);
        
        InetSocketAddress address = new InetSocketAddress(host, 443);
        proxySocket.connect(address);
        
        SSLSocketFactory sslSocketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
        socket = sslSocketFactory.createSocket(proxySocket, address.getHostName(), address.getPort(), true);
        ((SSLSocket)socket).startHandshake();
        
	}
		
	public void buildSSl(String host) throws UnknownHostException, IOException {
		SSLSocketFactory factory =
                (SSLSocketFactory)SSLSocketFactory.getDefault();
            socket =
                (Socket)factory.createSocket(host, 443);
        this.host = host; 
	}

	
	public void build(String host, int port) throws UnknownHostException, IOException {
		socket = new Socket(host,port);
		this.host = host;
	}
	
	public void addProperty(String property, String valor) {
		properties.put(property, valor);
	}
	
	public void execute(String path) throws IOException {
		PrintWriter wtr = new PrintWriter(socket.getOutputStream());
		
		//Prints the request string to the output stream
        wtr.println("GET " + path + " HTTP/1.1");
        wtr.println("Host: api-reune-pruebas.condusef.gob.mx");
        for (String key: properties.keySet()) {
        	wtr.println(key + ": " + properties.get(key));
        }
        wtr.println("");
        wtr.flush();
        
	}
	
	public void execute(String path, String body) throws IOException {
		PrintWriter wtr = new PrintWriter(socket.getOutputStream());
		//Prints the request string to the output stream
        wtr.println("GET " + path + " HTTP/1.1");
        wtr.println("Host: " + host);
        for (String key: properties.keySet()) {
        	wtr.println(key + ": " + properties.get(key));
        }
        wtr.println("Content-Length: " + body.length());
        wtr.println("");
        wtr.println(body);
        wtr.println("");
        wtr.flush();
        
        readResponse();
	}
	
	public void readResponse() throws IOException {
		BufferedReader bufRead = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        
		String outStr = null;
		// lectura de propiedades de resultado 
		while((outStr = bufRead.readLine()) != null && !outStr.equals("")){
            //System.out.println(outStr);
            if (outStr.contains("HTTP")) {
            	String[] results = outStr.split(" ");
            	if (results.length >= 2)
            		codeResult = Integer.valueOf(results[1]).intValue();
            	if (results.length >= 3)
            		msgResult = results[2];
            }
        }
		/*
		System.out.println("Code result: " + codeResult);
		System.out.println("Msg result: " + msgResult);
		System.out.println("Leyendo el body");*/
		// lectura del body
		body = "";
		while((outStr = bufRead.readLine()) != null && !outStr.equals("")){
            //System.out.println(outStr);
            body = body + outStr;
            
        }
		// System.out.println("body: " + body);
	}
}
