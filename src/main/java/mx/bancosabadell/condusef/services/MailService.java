package mx.bancosabadell.condusef.services;

public abstract class MailService {

	public abstract boolean send(String to, String subject, String message);
	
}
