package mx.bancosabadell.condusef.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class QuejaInfoResponse {

    @JsonProperty("QuejasNoTrim")
    private int quejasNoTrim;

    @JsonProperty("QuejasNum")
    private int quejasNum;

    @JsonProperty("QuejasFolio")
    private String quejasFolio;

    @JsonProperty("QuejasFecRecepcion")
    private String quejasFecRecepcion;

    @JsonProperty("MedioId")
    private int medioId;

    @JsonProperty("NivelATId")
    private int nivelATId;

    @JsonProperty("product")
    private String product;

    @JsonProperty("CausasId")
    private String causasId;

    @JsonProperty("QuejasPORI")
    private String quejasPORI;

    @JsonProperty("QuejasEstatus")
    private int quejasEstatus;

    @JsonProperty("EstadosId")
    private int estadosId;

    @JsonProperty("QuejasMunId")
    private long quejasMunId;

    @JsonProperty("QuejasLocId")
    private long quejasLocId;

    @JsonProperty("QuejasColId")
    private long quejasColId;

    @JsonProperty("QuejasCP")
    private int quejasCP;

    @JsonProperty("QuejasTipoPersona")
    private int quejasTipoPersona;

    @JsonProperty("QuejasSexo")
    private String quejasSexo;

    @JsonProperty("QuejasEdad")
    private int quejasEdad;

    @JsonProperty("QuejasFecResolucion")
    private String quejasFecResolucion;

    @JsonProperty("QuejasFecNotificacion")
    private String quejasFecNotificacion;

    @JsonProperty("QuejasRespuesta")
    private String quejasRespuesta;

    @JsonProperty("QuejasNumPenal")
    private int quejasNumPenal;

    @JsonProperty("PenalizacionId")
    private int penalizacionId;

    private List<String> errors;

}
