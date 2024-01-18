package mx.bancosabadell.condusef.services;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mx.bancosabadell.condusef.config.ConfigConstants;
import mx.bancosabadell.condusef.models.Queja;

/* Por definir funcionalidad */
public class CondusefBussines {



    private static final Logger logger = LoggerFactory.getLogger(CondusefBussines.class);
    
    public String urlNas = ConfigConstants.DIR_NAS;
    public String urlNasLog = ConfigConstants.DIR_NAS_LOGS;
    public String urlHistorico = ConfigConstants.DIR_NAS_HISTORICO;


    public Queja mapperDocumentQueja() throws ParseException{
        //Por defini funcionalidad para convertir documento a json
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String fechaString = "10/01/2024";        
        Date fecha = formato.parse(fechaString);

        logger.info("Se convierte el documento a queja");
        Queja dommiQueja = new Queja();
        dommiQueja.setQuejasNoTrim(5); // Mayo
        dommiQueja.setQuejasNum(1);
        dommiQueja.setQuejasFolio("F0001");
        dommiQueja.setQuejasFecRecepcion(fecha); // Usar la fecha
        dommiQueja.setMedioId(1);
        dommiQueja.setNivelATId(2);
        dommiQueja.setProduct("026911891272");
        dommiQueja.setCausasId("C001");
        dommiQueja.setQuejasPORI("SI");
        dommiQueja.setQuejasEstatus(1); // Pendiente
        dommiQueja.setEstadosId(9); // Ejemplo de entidad federativa
        dommiQueja.setQuejasMunId(12345678);
        dommiQueja.setQuejasLocId(12345678);
        dommiQueja.setQuejasColId(12345678);
        dommiQueja.setQuejasCP(12345);
        dommiQueja.setQuejasTipoPersona(1); // Persona Física
        dommiQueja.setQuejasSexo("H");
        dommiQueja.setQuejasEdad(30);
        dommiQueja.setQuejasFecResolucion(fecha); // Usar la fecha 
        dommiQueja.setQuejasFecNotificacion(fecha); // Usar la fecha 
        dommiQueja.setQuejasRespuesta(null); // Nulo ya que está pendiente
        dommiQueja.setQuejasNumPenal(0);
        dommiQueja.setPenalizacionId(1);
        logger.info(dommiQueja.toString());
        return dommiQueja;
        
    }
   
}