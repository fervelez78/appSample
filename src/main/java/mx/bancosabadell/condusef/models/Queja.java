package mx.bancosabadell.condusef.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;


import lombok.Data;

@Data
public class Queja {
	@Size( min=1, max = 400, message = "El numero máximo de caracteres de la denominación debe ser 400")
	private String quejasDenominacion;
	
	@Size( min=1, max = 200, message = "El numero máximo de caracteres del sector debe ser 200")
	private String quejasSector;

	@Max(value = 12, message = "El numero de mes debe ser menor o igual a 12")
    private Integer quejasNoMes;
    
    @Digits(integer = 1, fraction = 0, message = "La longitud maxima de QuejasNum debe ser de 1")
    private Integer quejasNum;

    @NotBlank(message = "Folio no puede estar vacío")
    private String quejasFolio;

    private String quejasFecRecepcion;

    @Positive(message = "Medio debe ser un número positivo")
    @Digits(integer = 2, fraction = 0, message = "La longitud maxima de medioId debe ser de 2")
    private Integer quejasMedio;

    @Positive(message = "Nivel de atención debe ser un número positivo")
    @Digits(integer = 2, fraction = 0, message = "La longitud maxima de nivelATId debe ser de 2")
    private Integer quejasNivelAT;

    @NotBlank(message = "Producto no puede estar vacío")
    @Size( min=12, max = 12, message = "El numero de caracteres de product debe ser de máximo 12")
    private String quejasProducto;

    @NotBlank(message = "Causa no puede estar vacío")
    @Size( min=1, max = 4, message = "El numero de caracteres de causasId debe ser de máximo 4 ")
    private String quejasCausa;

    @NotBlank(message = "PORI no puede estar vacío")
    @Pattern(regexp = "^(SI|NO)$", message = "quejasPORI debe ser 'SI' o 'NO' (mayúsculas)")
    private String quejasPORI;

    private Integer quejasEstatus;

    @Min(value = 1, message = "estadosId debe ser mayor o igual a 1")
    @Digits(integer = 2, fraction = 0, message = "La longitud maxima de estadosId debe ser de 2")
    private Integer quejasEstados;

    @Min(value = 1, message = "quejasMunId debe ser mayor o igual a 1")
    @Digits(integer = 8, fraction = 0, message = "La longitud maxima de estadosId debe ser de 8")
    private Integer quejasMunId;

    @Min(value = 1, message = "quejasLocId debe ser mayor o igual a 1")
    @Digits(integer = 8, fraction = 0, message = "La longitud maxima de quejasLocId debe ser de 8")
    private Integer quejasLocId;

    @Min(value = 1, message = "quejasColId debe ser mayor o igual a 1")
    @Digits(integer = 8, fraction = 0, message = "La longitud maxima de quejasColId debe ser de 8")
    private Integer quejasColId;

    @Min(value = 1, message = "quejasCP debe ser mayor o igual a 1")
    @Digits(integer = 10, fraction = 0, message = "La longitud maxima de quejasCP debe ser de 10")
    private Integer quejasCP;

    @Min(value = 1, message = "quejasTipoPersona debe ser mayor o igual a 1")
    @Max(value = 2, message = "quejasTipoPersona debe ser menor o igual a 2")
    private Integer quejasTipoPersona;

    @Pattern(regexp = "[HM]|", message = "quejasSexo debe ser 'H' o 'M'")
    private String quejasSexo;

    @Min(value = 0, message = "quejasEdad debe ser mayor o igual a 0")
    @Max(value = 150, message = "quejasEdad debe ser menor o igual a 150")
    private Integer quejasEdad;

    private String quejasFecResolucion;

    private String quejasFecNotificacion;

    @Min(value = 1, message = "quejasRespuesta debe ser mayor o igual a 1")
    @Max(value = 3, message = "quejasRespuesta debe ser menor o igual a 3")
    private Integer quejasRespuesta;

    @Min(value = 0, message = "quejasNumPenal debe ser mayor o igual a 0")
    @Digits(integer = 4, fraction = 0, message = "La longitud maxima de quejasNumPenal debe ser de 4")
    private Integer quejasNumPenal;
    
    private Integer quejasPenalizacion;

    public Queja(
            String quejasDenominacion, String quejasSector,
    		Integer quejasNoTrim, Integer quejasNum, 
    		String quejasFolio, String quejasFecRecepcion, Integer quejasMedio,
            Integer quejasNivelAT, String quejasProduct, String quejasCausa, 
            String quejasPORI, Integer quejasEstatus, Integer quejasEstados,
            Integer quejasMunId, Integer quejasLocId, Integer quejasColId, 
            Integer quejasCP, Integer quejasTipoPersona, String quejasSexo, 
            Integer quejasEdad, String quejasFecResolucion, String quejasFecNotificacion,
            Integer quejasRespuesta, Integer quejasNumPenal, Integer quejasPenalizacion) {
        
    	//
    	this.quejasDenominacion = quejasDenominacion;
        this.quejasSector = quejasSector;
    	this.quejasNoMes = quejasNoTrim;
        this.quejasNum = quejasNum;
        this.quejasFolio = quejasFolio;
        this.quejasFecRecepcion = quejasFecRecepcion;
        this.quejasMedio = quejasMedio;
        this.quejasNivelAT = quejasNivelAT;
        this.quejasProducto = quejasProduct;
        this.quejasCausa = quejasCausa;
        this.quejasPORI = quejasPORI;
        this.quejasEstatus = quejasEstatus;
        this.quejasEstados = quejasEstados;
        this.quejasMunId = quejasMunId;
        this.quejasLocId = quejasLocId;
        this.quejasColId = quejasColId;
        this.quejasCP = quejasCP;
        this.quejasTipoPersona = quejasTipoPersona;
        this.quejasSexo = quejasSexo;
        this.quejasEdad = quejasEdad;
        this.quejasFecResolucion = quejasFecResolucion;
        this.quejasFecNotificacion = quejasFecNotificacion;
        this.quejasRespuesta = quejasRespuesta;
        this.quejasNumPenal = quejasNumPenal;
        this.quejasPenalizacion = quejasPenalizacion;
    }

    public Queja() {
        //TODO Auto-generated constructor stub
    }

    public Date parseFecha(String fecha) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            return sdf.parse(fecha);
        } catch (ParseException e) {
        	throw new IllegalArgumentException("Formato de fecha inválido: " + fecha);
        }
    }
    
}
