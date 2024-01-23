package mx.bancosabadell.condusef.services;



import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.validation.Validator;

import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import mx.bancosabadell.condusef.config.ConfigConstants;
import mx.bancosabadell.condusef.models.InfoValidate;
import mx.bancosabadell.condusef.models.Queja;
import mx.bancosabadell.condusef.models.ResponseRedeco;


public class CondusefBussines {



    
    private static final Logger loggerRedeco = LoggerFactory.getLogger("clientRedecoLogger");
    
    public String urlNas = ConfigConstants.DIR_NAS;
    public String pathRedeco = ConfigConstants.DIR_NAS_REDECO;
    public String urlNasLog = ConfigConstants.DIR_NAS_LOGS;
    public String urlHistorico = ConfigConstants.DIR_NAS_HISTORICO;


   /*  public Queja mapperDocumentQuejaDummi(String folio){
        
        loggerRedeco.info("Inicio: Se convierte el documento a queja");
        parseDocumentToBen();
        //! Asignacion de valiable dummi
        String fechaString = "10/01/2024";        
       
        Integer quejasNoTrim = 1 ; 
        Integer quejasNum = 1;
        String quejasFolio = folio;//F0001
        String quejasFecRecepcion = fechaString;
        Integer medioId = 1;
        Integer nivelATId = 2;
        String product = "0269118912722";
        String causasId = "C001";
        String quejasPORI = "SI";
        Integer quejasEstatus = 1;
        Integer estadosId = 9 ;
        Integer quejasMunId = 12345678;
        Integer quejasLocId = 12345678;
        Integer quejasColId = 12345678;
        Integer quejasCP = 1234;
        Integer quejasTipoPersona = 1;
        String quejasSexo = "H";
        Integer quejasEdad = 30;
        String quejasFecResolucion =  fechaString;
        String quejasFecNotificacion =  fechaString;
        Integer quejasRespuesta = null;
        Integer quejasNumPenal = 0 ;
        Integer penalizacionId = 5;

        Queja queja = null;
          

            queja = new Queja(
                quejasNoTrim,
                quejasNum,
                quejasFolio,
                quejasFecRecepcion,
                medioId,
                nivelATId,
                product,
                causasId,
                quejasPORI,
                quejasEstatus,
                estadosId,
                quejasMunId,
                quejasLocId,
                quejasColId,
                quejasCP,
                quejasTipoPersona,
                quejasSexo,
                quejasEdad,
                quejasFecResolucion,
                quejasFecNotificacion,
                quejasRespuesta,
                quejasNumPenal,
                penalizacionId
                );
        return validateQueja(queja);        
    } */
    
    
    public ResponseRedeco mapperDocumentQueja(List<QuejasData> quejasDataList){
        ResponseRedeco responseRedeco = new ResponseRedeco();
        List<InfoValidate> listInfoValidate = new ArrayList<>();
        List<Queja> quejaList = new ArrayList<>();
        
        for (QuejasData quejasData : quejasDataList) {            
            Queja queja = new Queja();

            queja.setQuejasNoTrim(Integer.parseInt(quejasData.getQuejasNoTrim())); // Mayo
            queja.setQuejasNum(Integer.parseInt(quejasData.getQuejasNum()));
            queja.setQuejasFolio(quejasData.getQuejasFolio());
            queja.setQuejasFecRecepcion(quejasData.getQuejasFecRecepcion()); // Usar la fecha
            queja.setMedioId(Integer.parseInt(quejasData.getMedioId()));
            queja.setNivelATId(Integer.parseInt(quejasData.getNivelATId()));
            queja.setProduct(quejasData.getProduct());
            queja.setCausasId(quejasData.getCausasId());
            queja.setQuejasPORI(quejasData.getQuejasPORI());
            queja.setQuejasEstatus(Integer.parseInt(quejasData.getQuejasEstatus())); // Pendiente
            queja.setEstadosId(Integer.parseInt(quejasData.getEstadosId())); // Ejemplo de entidad federativa
            queja.setQuejasMunId(Integer.parseInt(quejasData.getQuejasMunId()));
            queja.setQuejasLocId(Integer.parseInt(quejasData.getQuejasLocId()));
            queja.setQuejasColId(Integer.parseInt(quejasData.getQuejasColId()));
            queja.setQuejasCP(Integer.parseInt(quejasData.getQuejasCP()));
            queja.setQuejasTipoPersona(Integer.parseInt(quejasData.getQuejasTipoPersona())); // Persona Física
            queja.setQuejasSexo(quejasData.getQuejasSexo());
            queja.setQuejasEdad(Integer.parseInt(quejasData.getQuejasEdad()));
            queja.setQuejasFecResolucion(quejasData.getQuejasFecResolucion()); // Usar la fecha 
            queja.setQuejasFecNotificacion(quejasData.getQuejasFecNotificacion()); // Usar la fecha 
            queja.setQuejasRespuesta(quejasData.getQuejasRespuesta()); // Nulo ya que está pendiente
            queja.setQuejasNumPenal(Integer.parseInt(quejasData.getQuejasNumPenal()));
            queja.setPenalizacionId(Integer.parseInt(quejasData.getPenalizacionId()));

            responseRedeco = validateQueja(queja, responseRedeco, listInfoValidate, quejaList);  
            
        }
        return responseRedeco;
    }

    public ResponseRedeco validateQueja(Queja queja, ResponseRedeco responseRedeco,  List<InfoValidate> listInfoValidate, List<Queja> quejaList){
        
        try{ 
            ValidatorFactory validatorFactory = Validation.byDefaultProvider()
               .configure()
               .messageInterpolator(new ParameterMessageInterpolator())
               .buildValidatorFactory();

               Validator validator = validatorFactory.getValidator();

               Set<ConstraintViolation<Queja>> violations = validator.validate(queja);

               
           if (!violations.isEmpty()) {
               for (ConstraintViolation<Queja> violation : violations) {                

                   InfoValidate errorinfoValidate = new InfoValidate("Error en queja con Folio: " + queja.getQuejasFolio() + "\n" + "Mensaje: " + violation.getMessage());

                   responseRedeco.setErrorsValidate(listInfoValidate);
                   responseRedeco.getErrorsValidate().add(errorinfoValidate);
                   loggerRedeco.info(errorinfoValidate.getMessageError());
               }   
           } else {

                responseRedeco.setQuejas(quejaList);
                responseRedeco.getQuejas().add(queja);
               loggerRedeco.info(queja.getQuejasFolio() + " Ok"); 
                              
           }
       } catch (Exception e) {
            InfoValidate errorinfoValidate = new InfoValidate("Error al procesar las quejas" + e.getCause());

            responseRedeco.getErrorsValidate().add(errorinfoValidate);
            loggerRedeco.error("Error al procesar las quejas", e.getCause());
           
       }
       
       return responseRedeco;
    }
    
    public List<QuejasData> parseDocumentToBen(){
        //!Mejorar captura de exepciones
        //Se buscan los archivos .txt desde la carpeta dada el cual regresa una lista de archivos
        File[] archivosEnDirectorio = findDocument();

        List<QuejasData> quejasDataList = new ArrayList<>();

        if (archivosEnDirectorio != null) {
            for (File archivo : archivosEnDirectorio) {
                if (archivo.isFile() && archivo.getName().toLowerCase().endsWith(".txt")) {
                    // Si se encuentra un archivo CSV, se intentara leerlo
                    try (FileReader reader = new FileReader(archivo)) {
                        CsvToBean<QuejasData> csvToBean = new CsvToBeanBuilder<QuejasData>(reader)
                                .withType(QuejasData.class)
                                .withSeparator(',')
                                .build();
                        //Se extrae en una lista cada "fila" Queja, del CSV el cual se mapea con CsvToBean
                        quejasDataList = csvToBean.parse();

                        /* // Ahora, quejasDataList contiene los objetos con los datos del CSV
                        for (QuejasData quejasData : quejasDataList) {
                            System.out.println(quejasData.toString());
                        } */
                        
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
            System.err.println("No se pudieron listar los archivos en el directorio.");
            return null;
        }
        return quejasDataList;

    }

    public File[] findDocument(){
        //!Mejorar captura de exepciones
        // Verifica si el directorio existe
        File directorioCsv = new File(urlNas+pathRedeco);
        if (!directorioCsv.isDirectory()) {
            System.err.println("La ruta especificada no es un directorio válido. " + urlNas+pathRedeco );
            return null;
        }

        // Lista de archivos en el directorio
        File[] archivosEnDirectorio = directorioCsv.listFiles();
        return archivosEnDirectorio;
    }

        
        
}
