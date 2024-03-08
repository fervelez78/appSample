package mx.bancosabadell.condusef.services;

import bantotal.dlya.com.uy.btsoa.CommonDataCrossSendMailResponse;

public interface LatiniaServiceEmail {

	public CommonDataCrossSendMailResponse sendEmail(String to, String subject, String message) throws Exception;

}
