package mx.bancosabadell.condusef.models;


import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;
@Data
public class ConsultaData {

    
    private String institucionClave;

   
    private String sector;

   
    private Integer consultasTrim;

   
    private Integer numConsultas;

    
    private String consultasFolio;

   
    private Integer consultasEstatusCon;

    private String consultasFecAten;

    private Integer estadosId;

    private String consultasFecRecepcion;

    private Integer mediosId;

    private String producto;

    private String causaId;

    private Long consultasCP;

    private Integer consultasMpioId;

    private Integer consultasLocId;

    private Integer consultasColId;

    private Integer consultascatnivelatenId;

    private String consultasPori;
}
