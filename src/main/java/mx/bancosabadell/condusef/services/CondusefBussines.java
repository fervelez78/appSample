package mx.bancosabadell.condusef.services;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

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

/**
 * Clase para establecer el negocio de Condusef.
 */
public class CondusefBussines {
    
	/**
	 * Logger de REDECO.
	 */
    private static final Logger loggerRedeco = LoggerFactory.getLogger("clientRedecoLogger");
    
    /**
     * Logger de REUNE.
     */
    private static final Logger loggerReune = LoggerFactory.getLogger("clientReuneLogger");
    
    /**
     * Recupera el directorio NAS.
     */
    public String urlNas = ConfigConstants.getDirNas();

    /**
     * Recupera el directorio REDECO.
     */
    public String pathRedeco = ConfigConstants.getDirNasRedeco();
    
    /**
     * Recupera el directorio REUNE.
     */
    public String pathReune = ConfigConstants.getDirNasReune();
    
    /**
     * Recupera el directorio para los logs.
     */
    public String urlNasLog = ConfigConstants.getDirNasLog();
    
    /**
     * Recupera el directorio del histórico.
     */
    public String urlHistorico = ConfigConstants.getDirNasHistorico();
    
    /**
     * Lista de archivos procesados
     */				
    public static List<File> archivosProcesados = new ArrayList<File>();

    /**
     * Nombre de la razón social que ejecuta el proceso
     */
    private String razonSocial;    
    
    /**
     * Constructor por defecto de la clase.
     * @param razonSocial razón social que ejecuta el proceso
     */
    public CondusefBussines(String razonSocial) {
    	setRazonSocial(razonSocial);
    }
    
    /**
     * Método para el mapeo de una lista quejasData a un ResponseRedeco.        
     * @param quejasDataList Lista de quejas recuperada de un archivo.
     * @return Estructura para el response de REDECO.
     */
    public ResponseRedeco mapperDocumentQueja(List<QuejasData> quejasDataList){
        
    	ResponseRedeco responseRedeco = new ResponseRedeco();
        List<InfoValidate> listInfoValidate = new ArrayList<>();
        List<Queja> quejaList = new ArrayList<>();
        
        try {
            for (QuejasData quejasData : quejasDataList) {            
                Queja queja = new Queja();
                queja.setQuejasDenominacion(quejasData.getQuejasDenominacion());
                queja.setQuejasSector(quejasData.getQuejasSector());
                queja.setQuejasNoMes(Integer.parseInt(quejasData.getQuejasNoMes())); // Mayo
                queja.setQuejasNum(Integer.parseInt(quejasData.getQuejasNum()));
                queja.setQuejasFolio(quejasData.getQuejasFolio());
                queja.setQuejasFecRecepcion(quejasData.getQuejasFecRecepcion()); // Usar la fecha
                queja.setQuejasMedio(Integer.parseInt(quejasData.getQuejasMedio()));
                queja.setQuejasNivelAT(Integer.parseInt(quejasData.getQuejasNivelAT()));
                queja.setQuejasProducto(quejasData.getQuejasProducto());
                queja.setQuejasCausa(quejasData.getQuejasCausa());
                queja.setQuejasPORI(quejasData.getQuejasPORI());
                queja.setQuejasEstatus(Integer.parseInt(quejasData.getQuejasEstatus())); // Pendiente
                queja.setQuejasEstados(Integer.parseInt(quejasData.getQuejasEstados())); // Ejemplo de entidad federativa
                queja.setQuejasMunId(Integer.parseInt(quejasData.getQuejasMunId()));
                queja.setQuejasLocId(Integer.parseInt(quejasData.getQuejasLocId()));
                queja.setQuejasColId(Integer.parseInt(quejasData.getQuejasColId()));
                queja.setQuejasCP(Integer.parseInt(quejasData.getQuejasCP()));
                queja.setQuejasTipoPersona(Integer.parseInt(quejasData.getQuejasTipoPersona())); // Persona Física
                // El sexo y edad solo aplica pra persona física
                if (queja.getQuejasTipoPersona() != 2) { 
	                queja.setQuejasSexo(quejasData.getQuejasSexo());
	                queja.setQuejasEdad(Integer.parseInt(quejasData.getQuejasEdad()));
                }
                
                loggerRedeco.info("Cargado queja " + queja.getQuejasFolio());
                responseRedeco = validateQueja(queja, responseRedeco, listInfoValidate, quejaList);  
            }
        } catch (Exception e) {
            loggerRedeco.error("Error: " + e.getMessage());
            handleUnexpectedException(e, responseRedeco);
        }
        return responseRedeco;
    }

    /**
     * Método para el mapeo de una lista ConsultaData a un ResponseReune.
     * @param consultaDataList Lista de consultas leidas de un archivo.
     * @return Estrucutra ResponseReune
     */
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
                //consultaList.add(consulta);

            }
        } catch (Exception e) {
            loggerRedeco.error("Error: " + e.getMessage());
            handleUnexpectedException(e, responseReune);
        }
        return responseReune;
    }

    /**
     * Método para generar un error en la lectura de archivo.
     * @param e Excepción generada.
     * @param responseRedeco Resultado del servicio REDECO.
     */
    private void handleUnexpectedException(Exception e, ResponseRedeco responseRedeco) {
        String errorMessage = "Error inesperado: " + "No se encontro archivo REPORTE REDECO.xlsx";
        loggerRedeco.error(errorMessage);
        List<Queja> quejas = new ArrayList<>();
        responseRedeco.setQuejas(quejas);
        responseRedeco.setError(errorMessage);
    }
    
    /**
     * Método para generar un error en la lectura de archivo.
     * @param e Excepción generada.
     * @param responseReune Resultado del servicio REUNE.
     */
    private void handleUnexpectedException(Exception e, ResponseReune responseReune) {
        String errorMessage = "Error inesperado: " + "No se encontro archivo REPORTE_REUNE.xlsx";
        loggerReune.error(errorMessage);
        List<Consulta> consultas = new ArrayList<>();
        responseReune.setConsultas(consultas);
        responseReune.setError(errorMessage);
    }

    /**
     * Método para la validación de un registro de quejas.
     * @param queja Queja generada.
     * @param responseRedeco Respuesta del servicio de REDECO.
     * @param listInfoValidate Lista de información con las validaciones.
     * @param quejaList Lista de quejas.
     * @return Respuesta de REDECO.
     */
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
    
    /**
     * Método para la validación de un registro de consulta.
     * @param consulta Consulta a validar.
     * @param responseReune Respuesta del servicio REUNE.
     * @param listInfoValidate Lista de validaciones.
     * @param consultaList Lista de consultas.
     * @return Respuesta de REUNE.
     */
    public ResponseReune validateConsulta(Consulta consulta, ResponseReune responseReune, List<InfoValidate> listInfoValidate, List<Consulta> consultaList) {
        try {
            ValidatorFactory validatorFactory = Validation.byDefaultProvider()
                .configure()
                .messageInterpolator(new ParameterMessageInterpolator())
                .buildValidatorFactory();
    
            Validator validator = validatorFactory.getValidator();
    
            Set<ConstraintViolation<Consulta>> violations = validator.validate(consulta);
    
            if (!violations.isEmpty()) {
            	responseReune.setErrorsValidate(listInfoValidate);
                for (ConstraintViolation<Consulta> violation : violations) {
    
                    InfoValidate errorinfoValidate = new InfoValidate("Error en consulta con Folio: " + consulta.getConsultasFolio() + "\n" + "Mensaje: " + violation.getMessage());
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
    
    /**
     * Lectura de un archivo XLS de Quejas para la API de REDECO.
     * @return Lista de quejas obtenida.
     * @throws Exception Excepción generada en caso de error al leer el archivo.
     */
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

                    } catch (IOException e) {
                    	loggerRedeco.error("Error: " + e.getMessage());
                    	for (StackTraceElement trace: e.getStackTrace()) {
                    		loggerRedeco.error(trace.toString());
                    	}
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

    /**
     * Método para la lectura de un archivo xls a una lista de quejas.
     * @return Lista de quejas.
     * @throws Exception Excepción generada en la lectura del archivo.
     */
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
                        	try {
	                            // Ignorar la primera fila si contiene encabezados
	                            if (row.getRowNum() == 0) continue;
	    
	                            QuejasData quejasData = new QuejasData();
	                            
	                            //!Corregir Mapeo   Cannot get a NUMERIC value from a STRING cell y orden de datos
	                            quejasData.setQuejasDenominacion(row.getCell(0).getStringCellValue());
	                            quejasData.setQuejasSector(row.getCell(1).getStringCellValue());
	                            quejasData.setQuejasNoMes(String.valueOf((int) row.getCell(2).getNumericCellValue()));
	                            quejasData.setQuejasNum(String.valueOf((int) row.getCell(3).getNumericCellValue()));
	                            quejasData.setQuejasFolio(row.getCell(4).getStringCellValue());
	                            quejasData.setQuejasFecRecepcion(formatDate(row.getCell(5).getDateCellValue()));
	                            quejasData.setQuejasMedio(String.valueOf((int) row.getCell(6).getNumericCellValue()));
	                            quejasData.setQuejasNivelAT(String.valueOf((int) row.getCell(7).getNumericCellValue()));
	                            quejasData.setQuejasProducto(row.getCell(8).getStringCellValue());
	                            if (row.getCell(9).getCellType() == CellType.NUMERIC) {
	                                // Si la celda contiene un valor numérico, lo convertimos a String                                     
	                                quejasData.setQuejasCausa(String.valueOf(row.getCell(9).getNumericCellValue()));
	                            } else if (row.getCell(9).getCellType() == CellType.STRING) {
	                                // Si la celda contiene una cadena, la obtenemos directamente 
	                                quejasData.setQuejasCausa(row.getCell(9).getStringCellValue());
	                            }                       
	                            quejasData.setQuejasPORI(row.getCell(10).getStringCellValue());
	                            quejasData.setQuejasEstatus(String.valueOf((int) row.getCell(11).getNumericCellValue()));
	                            quejasData.setQuejasEstados(String.valueOf((int) row.getCell(12).getNumericCellValue()));
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
	                            quejasData.setQuejasPenalizacion(String.valueOf((int) row.getCell(24).getNumericCellValue()));
	
	                            quejasDataList.add(quejasData);
                        	}catch(NullPointerException e) {
                        		loggerRedeco.info(e.getMessage());
                        	}
                        }
                        // cerrando el archivo
                        loggerRedeco.info("Cerrando el archivo " + archivo.getPath() + " -> " + urlNas + pathRedeco + urlHistorico + archivo.getName());
                        try {
                        	workbook.close();
                        	fis.close();
                        }catch(Exception e) {
                        	loggerRedeco.info(e.getMessage());
                        }
                        // Agregamos el archivo a los procesados
                        archivosProcesados.add(archivo);
                    } catch (IOException | EncryptedDocumentException e) {
                    	loggerRedeco.error("Error: " + e.getMessage());
                    	for (StackTraceElement trace: e.getStackTrace()) {
                    		loggerRedeco.error(trace.toString());
                    	}
                    }
                }
            }
        } else {
            loggerRedeco.error("No se pudieron listar los archivos en el directorio.");
            return null;
        }
        return quejasDataList;
    }

    
    /**
     * Creamos nuestro filtro de archivos o ficheros
     */
    FileFilter archivosFilter = new FileFilter() {
        //Sobreescribimos el método
        public boolean accept(File file) {
        	String nombreArchivoEsperado = "";
        	
        	// Nombre del archivo los nombres aceptados son: 
            // REDECO_QUEJAS.xlsx, REDECO_QUEJAS_[YYYYMMDD].xlsx, REDECO_QUEJAS_[YYYYMMDDHHMM].xlsx        
        	if (file.getPath().toUpperCase().indexOf("REDECO") > 0) {
        		// obtiene la expresión regular para quejas de redeco Sabadell
        		if (getRazonSocial().trim().toUpperCase().equals("SABADELL"))
        			nombreArchivoEsperado = ConfigConstants.getRegExpQuejasRedecoSabadell();
        		// obtiene la expresión regular para quejas de redeco Sofom
        		if (getRazonSocial().trim().toUpperCase().equals("SOFOM"))
        			nombreArchivoEsperado = ConfigConstants.getRegExpQuejasRedecoSofom();
            	loggerRedeco.info("nombreArchivoEsperado: " + nombreArchivoEsperado);
        	// Nombre del archivo los nombres aceptados son: 
            // REPORTE REUNE CONSULTAS.xlsx, REPORTE REUNE CONSULTAS_[YYYYMMDD].xlsx, REPORTE REUNE CONSULTAS_[YYYYMMDDHHMM].xlsx
        	}else {
        		// obtiene la expresión regular para quejas de redeco Sabadell
        		if (getRazonSocial().trim().toUpperCase().equals("SABADELL"))
        			nombreArchivoEsperado = ConfigConstants.getRegExpQuejasReuneSabadell();
        		if (getRazonSocial().trim().toUpperCase().equals("SOFOM"))
        			nombreArchivoEsperado = ConfigConstants.getRegExpQuejasReuneSofom();

            	loggerReune.info("nombreArchivoEsperado: " + nombreArchivoEsperado);
        	}
        	if (file.getName().matches(nombreArchivoEsperado)) {
                return true;
            }
            return false;
        }
    };

    /**
     * Método para la lectura de un archivo xls y recuperar un alista de consultas REUNE.
     * @return Lista de consultas REUNE.
     * @throws Exception Excepción generada en caso de algun error en la lectura del archivo.
     */
    public List<ConsultaData> parseDocumentToBenXlsxReune() throws Exception {
        loggerReune.info("pathRedeco: " + pathRedeco);
    	List<File> archivosEnDirectorio = findDocument(pathReune);
        List<ConsultaData> consultasList = new ArrayList<>();

        if (archivosEnDirectorio != null) {
            for (File archivo : archivosEnDirectorio) {
                if (archivo.isFile() && archivo.getName().toLowerCase().endsWith(".xlsx")) {
                    try (FileInputStream fis = new FileInputStream(archivo);
                         Workbook workbook = new XSSFWorkbook(fis)) {

                        Sheet sheet = workbook.getSheetAt(0);

                        int totalFilas = sheet.getPhysicalNumberOfRows();

                        loggerReune.info("totalFilas: " + totalFilas);
                        // Iterar sobre cada fila
                        for (int i = 1; i < totalFilas; i++) {
                            Row row = sheet.getRow(i);
                            
                            ConsultaData consultaData = new ConsultaData();
                            if (row.getCell(0) != null) {
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
	                            consultaData.setProducto(row.getCell(10).getStringCellValue().trim());
	
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
                        }
                        loggerReune.info("Registros leidos: " + consultasList.size());
                        // Cerrar el archivo
                        loggerReune.info("Cerrando el archivo " + archivo.getPath() + " -> " + urlNas + pathReune + urlHistorico + archivo.getName());

                        try {
                        	workbook.close();
                        	fis.close();
                        }catch(Exception e) {
                        	loggerReune.info(e.getMessage());
                        }
                        archivosProcesados.add(archivo);

                    } catch (IOException | EncryptedDocumentException e) {
                        loggerReune.error("Error: " + e.getMessage());
                    	for (StackTraceElement trace: e.getStackTrace()) {
                    		loggerRedeco.error(trace.toString());
                    	}
                    }
                }
            }
        } else {
            loggerReune.error("No se pudieron listar los archivos en el directorio.");
            return null;
        }

        return consultasList;
    }
    
    /**
     * Método par ala busqueda de un archivo. 
     * @param pathNasDirectoriReuneOrRedeco Directorio donde se buscará el archivo.
     * @return Lista de archivos encontrados.
     * @throws Exception Excepción en caso de que se genere un error al leer directorio.
     */
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
        	if (pathNasDirectoriReuneOrRedeco == pathRedeco)
        		loggerRedeco.error(error);
        	if (pathNasDirectoriReuneOrRedeco == pathReune)
        		loggerReune.error(error);
            throw new FileNotFoundException(error);
        	
        }

    }

    /**
     * Método para dar formato a una fecha.
     * @param fecha Fecha a formatear.
     * @return Regresa la fecha con formato dd/mm/yyyy
     */
    public String formatDate (Date fecha){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String fechaFormateada = dateFormat.format(fecha);
        return fechaFormateada;
    }

    /**
     * Obtiene la razón social que ejecuta el proceso
     * @return Razón social que ejecuta el proceso
     */
	public String getRazonSocial() {
		return razonSocial;
	}

	/**
	 * Define la razón social que ejecuta el proceso.
	 * @param razonSocial Razón social que ejecuta el proceso
	 */
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
    
}
