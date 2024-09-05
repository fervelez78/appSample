package mx.bancosabadell.condusef.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ConsultaInfoResponse {

    @JsonProperty("InstitucionClave")
    private String institucionClave;

    @JsonProperty("Sector")
    private String sector;

    @JsonProperty("ConsultasTrim")
    private int consultasTrim;

    @JsonProperty("NumConsultas")
    private int numConsultas;

    @JsonProperty("ConsultasFolio")
    private String consultasFolio;

    @JsonProperty("ConsultasEstatusCon")
    private int consultasEstatusCon;

    @JsonProperty("ConsultasFecAten")
    private String consultasFecAten;

    @JsonProperty("EstadosId")
    private int estadosId;

    @JsonProperty("ConsultasFecRecepcion")
    private String consultasFecRecepcion;

    @JsonProperty("MediosId")
    private int mediosId;

    @JsonProperty("Producto")
    private String producto;

    @JsonProperty("CausaId")
    private String causaId;

    @JsonProperty("ConsultasCP")
    private int consultasCP;

    @JsonProperty("ConsultasMpioId")
    private long consultasMpioId;

    @JsonProperty("ConsultasLocId")
    private long consultasLocId;

    @JsonProperty("ConsultasColId")
    private long consultasColId;

    @JsonProperty("ConsultascatnivelatenId")
    private int consultascatnivelatenId;

    @JsonProperty("ConsultasPori")
    private String consultasPori;

    private List<String> errors;
}
