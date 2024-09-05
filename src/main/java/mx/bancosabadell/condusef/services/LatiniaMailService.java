package mx.bancosabadell.condusef.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bantotal.dlya.com.uy.btsoa.Authenticate;
import bantotal.dlya.com.uy.btsoa.AuthenticateExecute;
import bantotal.dlya.com.uy.btsoa.AuthenticateExecuteResponse;
import bantotal.dlya.com.uy.btsoa.AuthenticateSoapPort;
import bantotal.dlya.com.uy.btsoa.CommonDataCross;
import bantotal.dlya.com.uy.btsoa.CommonDataCrossSendMail;
import bantotal.dlya.com.uy.btsoa.CommonDataCrossSendMailResponse;
import bantotal.dlya.com.uy.btsoa.CommonDataCrossSoapPort;
import bantotal.dlya.com.uy.btsoa.SBSSendMail;
import bantotal.dlya.com.uy.btsoa.SBTErrorNegocio;
import bantotal.dlya.com.uy.btsoa.SBTInReq;

public class LatiniaMailService extends MailService {
	
    /**
     * Logger general de Condusef.
     */
	private static final Logger logger = LoggerFactory.getLogger("condusefLogger");

	/**
	 * Método para generar el token de autenticación para el uso del servicio del correo. 
	 * @return Token generado.
	 * @throws Exception Error generado en caso de fallar el consumo del servicio.
	 */
	public AuthenticateExecuteResponse getToken() throws Exception {

        AuthenticateExecuteResponse responseToken;

        Authenticate ws = new Authenticate();

        AuthenticateSoapPort wsPort = ws.getAuthenticateSoapPort();
        logger.info("  wsPort: " + wsPort.toString());
        AuthenticateExecute wsRequest = new AuthenticateExecute();
        
        SBTInReq bSbtInReq = new SBTInReq();

        bSbtInReq.setCanal("Movil");
        bSbtInReq.setRequerimiento("1");
        bSbtInReq.setUsuario("SVC_S_BTSERV");
        bSbtInReq.setDevice("1");
        logger.info("  bSbtInReq: " + bSbtInReq.toString());

        wsRequest.setUserId("SVC_S_BTSERV");
        wsRequest.setUserPassword("SVC_S_BTSERV");
        wsRequest.setBtinreq(bSbtInReq);
        logger.info("  wsRequest: " + wsRequest.toString());
        logger.info("  wsRequest.getUserId(): " + wsRequest.getUserId());
        logger.info("  wsRequest.getUserPassword(): " + wsRequest.getUserPassword());
        
        responseToken = wsPort.execute(wsRequest);
        
        if (responseToken == null)
        	throw new Exception("Error al tratar de generar el token");
        else if (responseToken.getErroresnegocio()!= null && 
        		responseToken.getErroresnegocio().getBTErrorNegocio().size() > 0) {
        	for (SBTErrorNegocio error: responseToken.getErroresnegocio().getBTErrorNegocio()) {
        		logger.info("Error: " + error.getCodigo() + error.getDescripcion());
        	}
        }else if (responseToken.getSessionToken() == null) {
        	throw new Exception("Error al obtener el token");
        }
        

        logger.info("Sesion Token: " + responseToken.getSessionToken());
        return responseToken;
    }

	/**
	 * Envío del correo al servicio de latinia.
	 * @param to Destinatario del correo. 
	 * @param subject Asunto del correo.
	 * @param message Mensaje del correo.
	 * @return Verdadero si se envía correctamente.
	 */
	@Override
	public boolean send(String to, String subject, String message) {
		// Inicia el envío del correo.
		CommonDataCross ws = new CommonDataCross();

        CommonDataCrossSoapPort wsPort = ws.getCommonDataCrossSoapPort();

        SBTInReq request = new SBTInReq();
        
        AuthenticateExecuteResponse token;
		try {
			token = getToken();
			System.out.println("Token: " + token.getSessionToken());
			System.out.println("Canal: " + token.getBtinreq().getCanal());
			System.out.println("Requerimiento: " + token.getBtinreq().getRequerimiento());
			System.out.println("Usuario: " + token.getBtinreq().getUsuario());
			System.out.println("Device: " + token.getBtinreq().getDevice());
	        request.setToken(token.getSessionToken());
	        request.setCanal(token.getBtinreq().getCanal());
	        request.setRequerimiento(token.getBtinreq().getRequerimiento());
	        request.setUsuario(token.getBtinreq().getUsuario());
	        request.setDevice(token.getBtinreq().getDevice());
	
	        SBSSendMail mail = new SBSSendMail();
	
	        mail.setSubject(subject);
	        mail.setMessageText(message);
	        mail.setTo(to);
	        mail.setEventCode((short) 90);
	
	        CommonDataCrossSendMail sendMail = new CommonDataCrossSendMail();
	
	        sendMail.setBtinreq(request);
	        sendMail.setMail(mail);
	
	        CommonDataCrossSendMailResponse response = new CommonDataCrossSendMailResponse();
	
	        response = wsPort.sendMail(sendMail);
	
	        return response.getSuccess().equals("success");
		} catch (Exception e) {
			logger.error(e.getMessage());
			for (StackTraceElement trace: e.getStackTrace()) {
        		logger.error(e.getMessage());
        		logger.error(trace.toString());
        	}
			return false;
		}
	}

}
