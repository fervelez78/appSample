package mx.bancosabadell.condusef.services;

import org.springframework.stereotype.Service;

import bantotal.dlya.com.uy.btsoa.Authenticate;
import bantotal.dlya.com.uy.btsoa.AuthenticateExecute;
import bantotal.dlya.com.uy.btsoa.AuthenticateExecuteResponse;
import bantotal.dlya.com.uy.btsoa.AuthenticateSoapPort;
import bantotal.dlya.com.uy.btsoa.SBTErrorNegocio;
import bantotal.dlya.com.uy.btsoa.SBTInReq;

@Service
public class LatiniaServiceImplToken implements LatiniaServiceToken{

	@Override
    public AuthenticateExecuteResponse getToken() throws Exception {

        AuthenticateExecuteResponse responseToken;
        Authenticate ws = new Authenticate();

        AuthenticateSoapPort wsPort = ws.getAuthenticateSoapPort();
        //System.out.println("  wsPort: " + wsPort.toString());
        AuthenticateExecute wsRequest = new AuthenticateExecute();

        SBTInReq bSbtInReq = new SBTInReq();

        bSbtInReq.setCanal("Movil");
        bSbtInReq.setRequerimiento("1");
        bSbtInReq.setUsuario("SVC_S_BTSERV");
        bSbtInReq.setDevice("1");
        //System.out.println("  bSbtInReq: " + bSbtInReq.toString());

        wsRequest.setUserId("SVC_S_BTSERV");
        wsRequest.setUserPassword("SVC_S_BTSERV");
        wsRequest.setBtinreq(bSbtInReq);

        //System.out.println("  wsRequest: " + wsRequest.toString());
        //System.out.println("  wsRequest.getUserId(): " + wsRequest.getUserId());
        //System.out.println("  wsRequest.getUserPassword(): " + wsRequest.getUserPassword());

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

}
