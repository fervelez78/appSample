package mx.bancosabadell.condusef.models;

import com.opencsv.bean.CsvBindByName;

import lombok.Data;

@Data
public class QuejasData {

    @CsvBindByName(column = "Denominación o razón social")
    private String quejasDenominacion;
    
    @CsvBindByName(column = "Denominación o razón social")
    private String quejasSector;

    @CsvBindByName(column = "Mes a informar")
    private String quejasNoMes;

    @CsvBindByName(column = "Número de quejas")
    private String quejasNum;

    @CsvBindByName(column = "Número de folio")
    private String quejasFolio;

    @CsvBindByName(column = "Fecha de la queja")
    private String quejasFecRecepcion;

    @CsvBindByName(column = "Medio de recepción o canal")
    private String quejasMedio;

    @CsvBindByName(column = "Nivel de atención o contacto")
    private String quejasNivelAT;

    @CsvBindByName(column = "Producto y/o Servicio")
    private String quejasProducto;

    @CsvBindByName(column = "Causa de la queja")
    private String quejasCausa;

    @CsvBindByName(column = "PORI")
    private String quejasPORI;

    @CsvBindByName(column = "Estado")
    private String quejasEstatus;

    @CsvBindByName(column = "Entidad Federativa")
    private String quejasEstados;

    @CsvBindByName(column = "Municipio o Alcaldía")
    private String quejasMunId;

    @CsvBindByName(column = "Colonia")
    private String quejasLocId;

    @CsvBindByName(column = "Código Postal")
    private String quejasColId;

    @CsvBindByName(column = "quejasCP")
    private String quejasCP;

    @CsvBindByName(column = "Tipo de persona")
    private String quejasTipoPersona;

    @CsvBindByName(column = "Sexo")
    private String quejasSexo;

    @CsvBindByName(column = "Edad")
    private String quejasEdad;

    @CsvBindByName(column = "Fecha de resolución")
    private String quejasFecResolucion;

    @CsvBindByName(column = "Fecha en la que se notificó al usuario")
    private String quejasFecNotificacion;

    @CsvBindByName(column = "Sentido de la resolución")
    private String quejasRespuesta;

    @CsvBindByName(column = " Número de penalización")
    private String quejasNumPenal;

    @CsvBindByName(column = " Tipo de penalización")
    private String quejasPenalizacion;

}
