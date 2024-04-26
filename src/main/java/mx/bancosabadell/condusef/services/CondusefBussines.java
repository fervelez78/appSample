package mx.bancosabadell.condusef.services;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.validation.Validator;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import mx.bancosabadell.condusef.config.ConfigConstants;
import mx.bancosabadell.condusef.models.Consulta;
import mx.bancosabadell.condusef.models.ConsultaData;
import mx.bancosabadell.condusef.models.InfoValidate;
import mx.bancosabadell.condusef.models.Queja;
import mx.bancosabadell.condusef.models.QuejasData;
import mx.bancosabadell.condusef.models.ResponseRedeco;
import mx.bancosabadell.condusef.models.ResponseReune;

public class CondusefBussines {
    
    private static final Logger loggerRedeco = LoggerFactory.getLogger("clientRedecoLogger");
    private static final Logger loggerReune = LoggerFactory.getLogger("clientReuneLogger");
    
    public String urlNas = ConfigConstants.DIR_NAS;

    public String pathRedeco = ConfigConstants.DIR_NAS_REDECO;
    public String pathReune = ConfigConstants.DIR_NAS_REUNE;
    
    public String urlNasLog = ConfigConstants.DIR_NAS_LOGS;
    
    public String urlHistorico = ConfigConstants.DIR_NAS_HISTORICO;

    /*
    public Queja mapperDocumentQuejaDummi(String folio){
        
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
        
        try {
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
                queja.setQuejasRespuesta(Integer.parseInt(quejasData.getQuejasRespuesta())); // Nulo ya que está pendiente
                queja.setQuejasNumPenal(Integer.parseInt(quejasData.getQuejasNumPenal()));
                queja.setPenalizacionId(Integer.parseInt(quejasData.getPenalizacionId()));
                loggerRedeco.info("Cargado queja " + queja.getQuejasFolio());
                responseRedeco = validateQueja(queja, responseRedeco, listInfoValidate, quejaList);  
            }
        } catch (Exception e) {
            loggerRedeco.error("Error: " + e.getMessage());
            handleUnexpectedException(e, responseRedeco);
        }
        return responseRedeco;
    }

    public ResponseReune mapperDocumentConsulta(List<ConsultaData> consultaDataList) {
        ResponseReune responseReune = new ResponseReune();
        List<InfoValidate> listInfoValidate = new ArrayList<>();
        List<Consulta> consultaList = new ArrayList<>();

        try {
            for (ConsultaData consultaData : consultaDataList) {
                Consulta consulta = new Consulta();
                consulta.setInstitucionClave(consultaData.getInstitucionClave());
                consulta.setSector(consultaData.getSector());
                consulta.setConsultasTrim(consultaData.getConsultasTrim());
                consulta.setNumConsultas(consultaData.getNumConsultas());
                consulta.setConsultasFolio(consultaData.getConsultasFolio());
                consulta.setConsultasEstatusCon(consultaData.getConsultasEstatusCon());
                consulta.setConsultasFecAten(consultaData.getConsultasFecAten());
                consulta.setEstadosId(consultaData.getEstadosId());
                consulta.setConsultasFecRecepcion(consultaData.getConsultasFecRecepcion());
                consulta.setMediosId(consultaData.getMediosId());
                consulta.setProducto(consultaData.getProducto());
                consulta.setCausaId(String.valueOf(consultaData.getCausaId()));
                consulta.setConsultasCP((consultaData.getConsultasCP()));
                consulta.setConsultasMpioId(consultaData.getConsultasMpioId());
                consulta.setConsultasLocId(consultaData.getConsultasLocId());
                consulta.setConsultasColId(consultaData.getConsultasColId());                
                consulta.setConsultascatnivelatenId(consultaData.getConsultascatnivelatenId());
                consulta.setConsultasPori(consultaData.getConsultasPori());

                // Validar la consulta antes de agregarla a la lista
                loggerReune.info("Cargando consulta " + consulta.getConsultasFolio());
                responseReune = validateConsulta(consulta, responseReune, listInfoValidate, consultaList);
                consultaList.add(consulta);

            }
        } catch (Exception e) {
            loggerRedeco.error("Error: " + e.getMessage());
            handleUnexpectedException(e, responseReune);
        }
        return responseReune;
    }

    private void handleUnexpectedException(Exception e, ResponseRedeco responseRedeco) {
        String errorMessage = "Error inesperado: " + "No se encontro archivo REPORTE REDECO.xlsx";
        loggerRedeco.error(errorMessage);
        List<Queja> quejas = new ArrayList<>();
        responseRedeco.setQuejas(quejas);
        responseRedeco.setError(errorMessage);
    }
    private void handleUnexpectedException(Exception e, ResponseReune responseReune) {
        String errorMessage = "Error inesperado: " + "No se encontro archivo REPORTE_REUNE.xlsx";
        loggerReune.error(errorMessage);
        List<Consulta> consultas = new ArrayList<>();
        responseReune.setConsultas(consultas);
        responseReune.setError(errorMessage);
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
               loggerRedeco.info("La carga de la queja con folio " + queja.getQuejasFolio() + "fue correcta"); 
                              
           }
       } catch (Exception e) {
            InfoValidate errorinfoValidate = new InfoValidate("Error al procesar las quejas" + e.getCause());

            responseRedeco.getErrorsValidate().add(errorinfoValidate);
            loggerRedeco.error("Error al procesar las quejas", e.getCause());
           
       }
       
       return responseRedeco;
    }
    
    public ResponseReune validateConsulta(Consulta consulta, ResponseReune responseReune, List<InfoValidate> listInfoValidate, List<Consulta> consultaList) {
        try {
            ValidatorFactory validatorFactory = Validation.byDefaultProvider()
                .configure()
                .messageInterpolator(new ParameterMessageInterpolator())
                .buildValidatorFactory();
    
            Validator validator = validatorFactory.getValidator();
    
            Set<ConstraintViolation<Consulta>> violations = validator.validate(consulta);
    
            if (!violations.isEmpty()) {
                for (ConstraintViolation<Consulta> violation : violations) {
    
                    InfoValidate errorinfoValidate = new InfoValidate("Error en consulta con Folio: " + consulta.getConsultasFolio() + "\n" + "Mensaje: " + violation.getMessage());
    
                    responseReune.setErrorsValidate(listInfoValidate);
                    responseReune.getErrorsValidate().add(errorinfoValidate);
                    loggerReune.info(errorinfoValidate.getMessageError());
                }
            } else {
                responseReune.setConsultas(consultaList);
                responseReune.getConsultas().add(consulta);
                loggerReune.info("La carga de la consulta con folio " + consulta.getConsultasFolio() + " fue correcta");
    
            }
        } catch (Exception e) {
            InfoValidate errorinfoValidate = new InfoValidate("Error al procesar las consultas" + e.getCause());
    
            responseReune.getErrorsValidate().add(errorinfoValidate);
            loggerReune.error("Error al procesar las consultas", e.getCause());
    
        }
    
        return responseReune;
    }
    public List<QuejasData> parseDocumentToBen() throws Exception{
        //!Mejorar captura de exepciones
        //Se buscan los archivos .txt desde la carpeta dada el cual regresa una lista de archivos
    	List<File> archivosEnDirectorio = findDocument(pathRedeco);

        List<QuejasData> quejasDataList = new ArrayList<>();

        if (archivosEnDirectorio != null) {
            for (File archivo : archivosEnDirectorio) {
                if (archivo.isFile() && archivo.getName().toLowerCase().endsWith(".xlsx")) {
                    // Si se encuentra un archivo CSV, se intentara leerlo
                    try (FileReader reader = new FileReader(archivo)) {
                        loggerRedeco.info("Leyendo archivo " + archivo.getName());
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
                    	loggerRedeco.error("Error: " + e.getMessage());
                        e.printStackTrace();
                    }
                }
            }
        } else {
        	String error = "Error: No se pudieron listar los archivos en el directorio.";
            loggerRedeco.info(error);
            throw new Exception(error);
        }
        return quejasDataList;

    }

    public List<QuejasData> parseDocumentToBenXlsx() throws Exception {
    	
    	List<File> archivosEnDirectorio = findDocument(pathRedeco);
        
        List<QuejasData> quejasDataList = new ArrayList<>();
    
        if (archivosEnDirectorio != null) {
            for (File archivo : archivosEnDirectorio) {
                if (archivo.isFile() && archivo.getName().toLowerCase().endsWith(".xlsx")) {
                    // Si se encuentra un archivo XLSX, se intentará leerlo
                    try (FileInputStream fis = new FileInputStream(archivo);
                         Workbook workbook = new XSSFWorkbook(fis)) {
    
                        Sheet sheet = workbook.getSheetAt(0);
                        
                        for (Row row : sheet) {
                            // Ignorar la primera fila si contiene encabezados
                            if (row.getRowNum() == 0) continue;
    
                            QuejasData quejasData = new QuejasData();
                            
                            //!Corregir Mapeo   Cannot get a NUMERIC value from a STRING cell y orden de datos 
                            quejasData.setQuejasNoTrim(String.valueOf((int) row.getCell(2).getNumericCellValue()));
                            quejasData.setQuejasNum(String.valueOf((int) row.getCell(3).getNumericCellValue()));
                            quejasData.setQuejasFolio(row.getCell(4).getStringCellValue());
                            quejasData.setQuejasFecRecepcion(formatDate(row.getCell(5).getDateCellValue()));
                            quejasData.setMedioId(String.valueOf((int) row.getCell(6).getNumericCellValue()));
                            quejasData.setNivelATId(String.valueOf((int) row.getCell(7).getNumericCellValue()));
                            quejasData.setProduct(row.getCell(8).getStringCellValue());
                            if (row.getCell(9).getCellType() == CellType.NUMERIC) {
                                // Si la celda contiene un valor numérico, lo convertimos a String                                     
                                quejasData.setCausasId(String.valueOf(row.getCell(9).getNumericCellValue()));
                            } else if (row.getCell(9).getCellType() == CellType.STRING) {
                                // Si la celda contiene una cadena, la obtenemos directamente 
                                quejasData.setCausasId(row.getCell(9).getStringCellValue());
                            }                       
                            quejasData.setQuejasPORI(row.getCell(10).getStringCellValue());
                            quejasData.setEstadosId(String.valueOf((int) row.getCell(11).getNumericCellValue()));
                            quejasData.setQuejasEstatus(String.valueOf((int) row.getCell(12).getNumericCellValue()));
                            quejasData.setQuejasMunId(String.valueOf((int) row.getCell(13).getNumericCellValue()));
                            quejasData.setQuejasLocId(String.valueOf((int) row.getCell(14).getNumericCellValue()));
                            quejasData.setQuejasColId(String.valueOf((int) row.getCell(15).getNumericCellValue()));
                            quejasData.setQuejasCP(String.valueOf((int) row.getCell(16).getNumericCellValue()));
                            quejasData.setQuejasTipoPersona(String.valueOf((int) row.getCell(17).getNumericCellValue()));
                            quejasData.setQuejasSexo(row.getCell(18).getStringCellValue());
                            quejasData.setQuejasEdad(String.valueOf((int) row.getCell(19).getNumericCellValue()));
                            quejasData.setQuejasFecResolucion(formatDate(row.getCell(20).getDateCellValue()));
                            quejasData.setQuejasFecNotificacion(formatDate(row.getCell(21).getDateCellValue()));                            
                            quejasData.setQuejasRespuesta(String.valueOf((int) row.getCell(22).getNumericCellValue()));
                            quejasData.setQuejasNumPenal(String.valueOf((int) row.getCell(23).getNumericCellValue()));
                            quejasData.setPenalizacionId(String.valueOf((int) row.getCell(24).getNumericCellValue()));

                            quejasDataList.add(quejasData);
                        }
                        // cerrando el archivo
                        loggerRedeco.info("Cerrando el archivo " + archivo.getPath() + " -> " + urlNas + pathRedeco + urlHistorico + archivo.getName());
                        try {
                        	workbook.close();
                        	fis.close();
                        }catch(Exception e) {
                        	loggerRedeco.info(e.getMessage());
                        }
                        // Movemos el archivo al histórico
                        loggerRedeco.info("Copiando archivo " + archivo.getPath());
                        Path origenPath = FileSystems.getDefault().getPath(archivo.getPath());
                        Path destinoPath = FileSystems.getDefault().getPath(urlNas + pathRedeco + urlHistorico + archivo.getName());
                        // Crear la carpeta historico si no existe
                        File historico = new File(urlNas + pathRedeco + urlHistorico);
                        if (!historico.exists()) {
                        	loggerRedeco.info("Creando direcotrio " + historico.getPath());
                        	historico.mkdir();
                        }
                        // Movemos el archivo a la carpeta de histórico
                        try {
                            Files.move(origenPath, destinoPath, StandardCopyOption.REPLACE_EXISTING);
                        } catch (IOException e) {
                            loggerRedeco.info(e.getMessage());
                        }
                    } catch (IOException | EncryptedDocumentException e) {
                    	loggerRedeco.error("Error: " + e.getMessage());
                    	e.printStackTrace();
                    }
                }
            }
        } else {
            loggerRedeco.error("No se pudieron listar los archivos en el directorio.");
            return null;
        }
        return quejasDataList;
    }

    

    // Creamos nuestro filtro de archivos o ficheros
    FileFilter archivosFilter = new FileFilter() {
        //Sobreescribimos el método
        public boolean accept(File file) {
            // Nombre del archivo los nombres aceptados son: 
            // REDECO_QUEJAS.xlsx, REDECO_QUEJAS_[YYYYMMDD].xlsx, REDECO_QUEJAS_[YYYYMMDDHHMM].xlsx
            String nombreArchivoEsperado = ConfigConstants.REG_EXP_QUEJAS_REDECO; 

        	if (file.getName().matches(nombreArchivoEsperado)) {
                return true;
            }
            return false;
        }
    };

    public List<ConsultaData> parseDocumentToBenXlsxReune() throws Exception {
        List<File> archivosEnDirectorio = findDocument(pathReune);
        List<ConsultaData> consultasList = new ArrayList<>();

        if (archivosEnDirectorio != null) {
            for (File archivo : archivosEnDirectorio) {
                if (archivo.isFile() && archivo.getName().toLowerCase().endsWith(".xlsx")) {
                    try (FileInputStream fis = new FileInputStream(archivo);
                         Workbook workbook = new XSSFWorkbook(fis)) {

                        Sheet sheet = workbook.getSheetAt(0);

                        int totalFilas = sheet.getPhysicalNumberOfRows();

                        // Iterar sobre cada fila
                        for (int i = 1; i < totalFilas; i++) {
                            Row row = sheet.getRow(i);
                            

                            ConsultaData consultaData = new ConsultaData();

                            // Establecer los atributos de la consulta
                            consultaData.setInstitucionClave(row.getCell(0).getStringCellValue());
                            consultaData.setSector(row.getCell(1).getStringCellValue());
                            consultaData.setConsultasTrim((int) row.getCell(2).getNumericCellValue());
                            consultaData.setNumConsultas((int) row.getCell(3).getNumericCellValue());
                            consultaData.setConsultasFolio(row.getCell(4).getStringCellValue());
                            consultaData.setConsultasEstatusCon((int) row.getCell(5).getNumericCellValue());

                            SimpleDateFormat formatoDeseado = new SimpleDateFormat("dd/MM/yyyy");

                            Date fechaExcel = row.getCell(6).getDateCellValue();
                            
                            String fechaFormateada = formatoDeseado.format(fechaExcel);

                            consultaData.setConsultasFecAten(fechaFormateada);

                            consultaData.setEstadosId((int) row.getCell(7).getNumericCellValue());
                          
                            fechaExcel = row.getCell(8).getDateCellValue();
                            fechaFormateada = formatoDeseado.format(fechaExcel);

                            consultaData.setConsultasFecRecepcion(fechaFormateada);

                            consultaData.setMediosId((int) row.getCell(9).getNumericCellValue());
                            consultaData.setProducto(row.getCell(10).getStringCellValue());

                            Cell cell = row.getCell(11);
                            String valorComoString = null;

                            if (cell != null) {
                                if (cell.getCellType() == CellType.NUMERIC) {
                                    // Si la celda contiene un valor numérico, lo convertimos a String
                                    valorComoString = String.valueOf((int) cell.getNumericCellValue());
                                } else if (cell.getCellType() == CellType.STRING) {
                                    // Si la celda contiene una cadena, la obtenemos directamente
                                    valorComoString = cell.getStringCellValue().trim();
                                }
                            }
                            consultaData.setCausaId(valorComoString);

                            consultaData.setConsultasCP((long)row.getCell(12).getNumericCellValue());
                            consultaData.setConsultasMpioId((int) row.getCell(13).getNumericCellValue());
                            if (row.getCell(14) != null) {
                                consultaData.setConsultasLocId((int) row.getCell(14).getNumericCellValue());
                            }
                            if (row.getCell(15) != null) {
                                consultaData.setConsultasColId((int) row.getCell(15).getNumericCellValue());
                            }
                            consultaData.setConsultascatnivelatenId((int) row.getCell(16).getNumericCellValue());
                            consultaData.setConsultasPori(row.getCell(17).getStringCellValue().toUpperCase());

                            
                            consultasList.add(consultaData);
                        }

                        // Cerrar el archivo
                        loggerReune.info("Cerrando el archivo " + archivo.getPath() + " -> " + urlNas + pathRedeco + urlHistorico + archivo.getName());

                        try {
                        	workbook.close();
                        	fis.close();
                        }catch(Exception e) {
                        	loggerReune.info(e.getMessage());
                        }

                          // Movemos el archivo al histórico
                          loggerReune.info("Copiando archivo " + archivo.getPath());

                          Path origenPath = FileSystems.getDefault().getPath(archivo.getPath());
                          Path destinoPath = FileSystems.getDefault().getPath(urlNas + pathRedeco + urlHistorico + archivo.getName());

                          // Crear la carpeta historico si no existe
                        File historico = new File(urlNas + pathRedeco + urlHistorico);
                        if (!historico.exists()) {
                        	loggerRedeco.info("Creando direcotrio " + historico.getPath());
                        	historico.mkdir();
                        }
                         // Movemos el archivo a la carpeta de histórico
                         try {
                            Files.move(origenPath, destinoPath, StandardCopyOption.REPLACE_EXISTING);
                        } catch (IOException e) {
                            loggerRedeco.info(e.getMessage());
                        }

                    } catch (IOException | EncryptedDocumentException e) {
                        loggerReune.error("Error: " + e.getMessage());
                    	e.printStackTrace();
                    }
                }
            }
        } else {
            loggerReune.error("No se pudieron listar los archivos en el directorio.");
            return null;
        }

        return consultasList;
    }
    
    public List<File> findDocument(String pathNasDirectoriReuneOrRedeco) throws Exception{
        // Directorio donde se copian los archivos
        File directorioCsv = new File(urlNas + pathNasDirectoriReuneOrRedeco);

        if (pathNasDirectoriReuneOrRedeco == pathRedeco) {
            
            loggerRedeco.info(urlNas + pathRedeco);
        } else {
            loggerReune.info(urlNas + pathReune);
            
        }
        
        if (!directorioCsv.isDirectory()) {
        	String error = "La ruta especificada no es un directorio válido. " + urlNas + pathNasDirectoriReuneOrRedeco;
            if (pathNasDirectoriReuneOrRedeco == pathRedeco) {            
                loggerRedeco.error(error);
            } else {
                loggerReune.error(error);
            }

            throw new Exception(error);
        }
                
        File[] files = directorioCsv.listFiles(archivosFilter);
        List<File> archEncontrados = new ArrayList<File>();

        //Listamos en un ciclo for los resultados
        for (File f : files) {
            
            if (pathNasDirectoriReuneOrRedeco == pathRedeco) {            
                loggerRedeco.info("Archivo encontrado: " + f.getName());
            } else {
                loggerReune.info("Archivo encontrado: " + f.getName());
            }
        	

            long previousSize = 0;

            int intentos = 0;
            while (intentos < 60) {
                long currentSize = f.length();

                if (currentSize == previousSize) {
                    // El tamaño del archivo se ha estabilizado, la copia probablemente ha finalizado
                    if (pathNasDirectoriReuneOrRedeco == pathRedeco) {            
                        loggerRedeco.info("El tamaño del archivo se ha estabilizado, la copia probablemente ha finalizado");
                    } else {
                        loggerReune.info("El tamaño del archivo se ha estabilizado, la copia probablemente ha finalizado");
                    }
                   
                	archEncontrados.add(f);
                    break;  // Salir del bucle
                }

                // Esperar 1 segundo antes de volver a verificar
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    // Manejar interrupciones si es necesario
                }

                previousSize = currentSize;
                intentos++;
            }
                        
        }
        
        if (archEncontrados.size() > 0) {
            return archEncontrados;
        }else{

        	String error = (pathNasDirectoriReuneOrRedeco == pathRedeco) ? "No se encontraron archivos para redeco quejas.": "No se encontraron archivos para reune consultas.";
            loggerRedeco.error(error);
            throw new Exception(error);
        }

    }

    public String formatDate (Date fecha){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String fechaFormateada = dateFormat.format(fecha);
        return fechaFormateada;
    }
    
}
