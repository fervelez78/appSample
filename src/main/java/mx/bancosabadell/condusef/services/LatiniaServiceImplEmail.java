package mx.bancosabadell.condusef.services;

import org.springframework.stereotype.Service;

import bantotal.dlya.com.uy.btsoa.AuthenticateExecuteResponse;
import bantotal.dlya.com.uy.btsoa.CommonDataCross;
import bantotal.dlya.com.uy.btsoa.CommonDataCrossSendMail;
import bantotal.dlya.com.uy.btsoa.CommonDataCrossSendMailResponse;
import bantotal.dlya.com.uy.btsoa.CommonDataCrossSoapPort;
import bantotal.dlya.com.uy.btsoa.SBSSendMail;
import bantotal.dlya.com.uy.btsoa.SBTInReq;


@Service
public class LatiniaServiceImplEmail implements LatiniaServiceEmail {
	
    
	@Override
    public CommonDataCrossSendMailResponse sendEmail(String to, String subject, String message) throws Exception {
    	
		LatiniaServiceToken tokenService = new LatiniaServiceImplToken();
		AuthenticateExecuteResponse token = tokenService.getToken();
    	
        CommonDataCross ws = new CommonDataCross();

        CommonDataCrossSoapPort wsPort = ws.getCommonDataCrossSoapPort();
        //System.out.println("  wsPort: " + wsPort.toString());

        SBTInReq request = new SBTInReq();

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

        return response;
    }

}
