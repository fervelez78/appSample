package mx.bancosabadell.condusef.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class Queja {

    
    @Max(value = 12, message = "QuejasNoTrim debe ser menor o igual a 12")
    private Integer quejasNoTrim;

    
    @Digits(integer = 1, fraction = 0, message = "La longitud maxima de QuejasNum debe ser de 1")
    private Integer quejasNum;

    @NotBlank(message = "quejasFolio no puede estar vacío")
    private String quejasFolio;

    @NotNull(message = "quejasFecRecepcion no puede ser nulo")
    private String quejasFecRecepcion;

    @Positive(message = "medioId debe ser un número positivo")
    private Integer medioId;

    @Positive(message = "nivelATId debe ser un número positivo")
    private Integer nivelATId;

    @NotBlank(message = "product no puede estar vacío")
    @Size( min=12, max = 12, message = "El numero de caracteres de product debe ser igual a 12")
    private String product;

    @NotBlank(message = "causasId no puede estar vacío")
    private String causasId;

    @NotBlank(message = "quejasPORI no puede estar vacío")
    private String quejasPORI;

    @Min(value = 1, message = "quejasEstatus debe ser mayor o igual a 1")
    @Max(value = 2, message = "quejasEstatus debe ser menor o igual a 2")
    private Integer quejasEstatus;

    @Min(value = 1, message = "estadosId debe ser mayor o igual a 1")
    private Integer estadosId;

    @Min(value = 1, message = "quejasMunId debe ser mayor o igual a 1")
    private Integer quejasMunId;

    @Min(value = 1, message = "quejasLocId debe ser mayor o igual a 1")
    private Integer quejasLocId;

    @Min(value = 1, message = "quejasColId debe ser mayor o igual a 1")
    private Integer quejasColId;

    @Min(value = 1, message = "quejasCP debe ser mayor o igual a 1")
    private Integer quejasCP;

    @Min(value = 1, message = "quejasTipoPersona debe ser mayor o igual a 1")
    @Max(value = 2, message = "quejasTipoPersona debe ser menor o igual a 2")
    private Integer quejasTipoPersona;

    @Pattern(regexp = "[HM]", message = "quejasSexo debe ser 'H' o 'M'")
    private String quejasSexo;

    @Min(value = 0, message = "quejasEdad debe ser mayor o igual a 0")
    @Max(value = 999, message = "quejasEdad debe ser menor o igual a 999")
    private Integer quejasEdad;

    @NotNull(message = "quejasFecResolucion no puede ser nulo")
    private String quejasFecResolucion;

    @NotNull(message = "quejasFecNotificacion no puede ser nulo")
    private String quejasFecNotificacion;

    @Min(value = 1, message = "quejasRespuesta debe ser mayor o igual a 1")
    @Max(value = 3, message = "quejasRespuesta debe ser menor o igual a 3")
    private Integer quejasRespuesta;

    @Min(value = 0, message = "quejasNumPenal debe ser mayor o igual a 0")
    private Integer quejasNumPenal;

    
    @Min(value = 1, message = "penalizacionId debe ser mayor o igual a 1")
    @Max(value = 3, message = "penalizacionId debe ser menor o igual a 3")
    private Integer penalizacionId;

    public Queja(
            Integer quejasNoTrim, Integer quejasNum, String quejasFolio, String quejasFecRecepcion, Integer medioId,
            Integer nivelATId, String product, String causasId, String quejasPORI, Integer quejasEstatus, Integer estadosId,
            Integer quejasMunId, Integer quejasLocId, Integer quejasColId, Integer quejasCP, Integer quejasTipoPersona,
            String quejasSexo, Integer quejasEdad, String quejasFecResolucion, String quejasFecNotificacion,
            Integer quejasRespuesta, Integer quejasNumPenal, Integer penalizacionId) {
        this.quejasNoTrim = quejasNoTrim;
        this.quejasNum = quejasNum;
        this.quejasFolio = quejasFolio;
        this.quejasFecRecepcion = quejasFecRecepcion;
        this.medioId = medioId;
        this.nivelATId = nivelATId;
        this.product = product;
        this.causasId = causasId;
        this.quejasPORI = quejasPORI;
        this.quejasEstatus = quejasEstatus;
        this.estadosId = estadosId;
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
        this.penalizacionId = penalizacionId;
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
