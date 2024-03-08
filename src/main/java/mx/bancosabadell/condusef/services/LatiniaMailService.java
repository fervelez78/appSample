package mx.bancosabadell.condusef.services;

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
	
	public AuthenticateExecuteResponse getToken() throws Exception {

        AuthenticateExecuteResponse responseToken;

            Authenticate ws = new Authenticate();

            AuthenticateSoapPort wsPort = ws.getAuthenticateSoapPort();
            System.out.println("  wsPort: " + wsPort.toString());
            AuthenticateExecute wsRequest = new AuthenticateExecute();
            
            SBTInReq bSbtInReq = new SBTInReq();

            bSbtInReq.setCanal("Movil");
            bSbtInReq.setRequerimiento("1");
            bSbtInReq.setUsuario("SVC_S_BTSERV");
            bSbtInReq.setDevice("1");
            System.out.println("  bSbtInReq: " + bSbtInReq.toString());

            wsRequest.setUserId("SVC_S_BTSERV");
            wsRequest.setUserPassword("SVC_S_BTSERV");
            wsRequest.setBtinreq(bSbtInReq);
            System.out.println("  wsRequest: " + wsRequest.toString());
            System.out.println("  wsRequest.getUserId(): " + wsRequest.getUserId());
            System.out.println("  wsRequest.getUserPassword(): " + wsRequest.getUserPassword());
            
            responseToken = wsPort.execute(wsRequest);
            
            if (responseToken == null)
            	throw new Exception("Error al tratar de generar el token");
            else if (responseToken.getErroresnegocio()!= null && 
            		responseToken.getErroresnegocio().getBTErrorNegocio().size() > 0) {
            	for (SBTErrorNegocio error: responseToken.getErroresnegocio().getBTErrorNegocio()) {
            		System.out.println("Error: " + error.getCodigo() + error.getDescripcion());
            	}
            }else if (responseToken.getSessionToken() == null) {
            	throw new Exception("Error al obtener el token");
            }
            

            System.out.println("Sesion Token: " + responseToken.getSessionToken());
            return responseToken;
    }

	@Override
	public boolean send(String to, String subject, String message) {
		// TODO Auto-generated method stub
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
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

}
