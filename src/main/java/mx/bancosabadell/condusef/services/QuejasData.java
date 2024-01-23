package mx.bancosabadell.condusef.services;

import com.opencsv.bean.CsvBindByName;

import lombok.Data;
@Data
public class QuejasData {
    @CsvBindByName(column = "quejasNoTrim")
    private String quejasNoTrim;

    @CsvBindByName(column = "quejasNum")
    private String quejasNum;

    @CsvBindByName(column = "quejasFolio")
    private String quejasFolio;

    @CsvBindByName(column = "quejasFecRecepcion")
    private String quejasFecRecepcion;

    @CsvBindByName(column = "medioId")
    private String medioId;

    @CsvBindByName(column = "nivelATId")
    private String nivelATId;

    @CsvBindByName(column = "product")
    private String product;

    @CsvBindByName(column = "causasId")
    private String causasId;

    @CsvBindByName(column = "quejasPORI")
    private String quejasPORI;

    @CsvBindByName(column = "quejasEstatus")
    private String quejasEstatus;

    @CsvBindByName(column = "estadosId")
    private String estadosId;

    @CsvBindByName(column = "quejasMunId")
    private String quejasMunId;

    @CsvBindByName(column = "quejasLocId")
    private String quejasLocId;

    @CsvBindByName(column = "quejasColId")
    private String quejasColId;

    @CsvBindByName(column = "quejasCP")
    private String quejasCP;

    @CsvBindByName(column = "quejasTipoPersona")
    private String quejasTipoPersona;

    @CsvBindByName(column = "quejasSexo")
    private String quejasSexo;

    @CsvBindByName(column = "quejasEdad")
    private String quejasEdad;

    @CsvBindByName(column = "quejasFecResolucion")
    private String quejasFecResolucion;

    @CsvBindByName(column = "quejasFecNotificacion")
    private String quejasFecNotificacion;

    @CsvBindByName(column = "quejasRespuesta")
    private Integer quejasRespuesta;

    @CsvBindByName(column = "quejasNumPenal")
    private String quejasNumPenal;

    @CsvBindByName(column = "penalizacionId")
    private String penalizacionId;
}
