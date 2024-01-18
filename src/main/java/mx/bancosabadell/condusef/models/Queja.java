package mx.bancosabadell.condusef.models;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class Queja {

    private int quejasNoTrim; // Mes a informar (1=Enero, 2=Febrero, ..., 12=Diciembre)
    private int quejasNum; // Número de quejas, valor por defecto 1
    private String quejasFolio; // Número de folio (Alfanumérico, máximo 50 caracteres)

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date quejasFecRecepcion; // Fecha de la queja, formato dd/mm/aaaa
    private int medioId; // Clave de Medio de recepción (Numérico)
    private int nivelATId; // Clave de Nivel de atención (Numérico)
    private String product; // Producto y/o servicio (Alfanumérico, máximo 12 caracteres)
    private String causasId; // Causa de la queja (Alfanumérico, máximo 4 caracteres)
    private String quejasPORI; // PORI, debe registrar "SI" o "NO" (Alfanumérico, máximo 2 caracteres)
    private int quejasEstatus; // Estado de la queja (1=Pendiente, 2=Concluido)
    private int estadosId; // Clave de Entidad federativa (Numérico)
    private long quejasMunId; // Clave del Municipio (Numérico)
    private long quejasLocId; // Clave de Localidad (Numérico)
    private long quejasColId; // Clave de Colonia (Numérico)
    private long quejasCP; // Código Postal (Numérico)
    private int quejasTipoPersona; // Tipo de persona (1=Persona Física, 2=Persona Moral)
    private String quejasSexo; // Sexo (H=Hombre, M=Mujer, solo para persona física)
    private Integer quejasEdad; // Edad, solo para persona física
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date quejasFecResolucion; // Fecha de resolución, formato dd/mm/aaaa
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date quejasFecNotificacion; // Fecha en la que se notificó al usuario, formato dd/mm/aaaa
    private Integer quejasRespuesta; // Sentido de la resolución (1, 2, 3, nulo si pendiente)
    private int quejasNumPenal; // Número de penalizaciones impuestas al despacho de cobranza
    private int penalizacionId; // Tipo de penalización (1, 2, 3)

    public Queja() {
    }

    
    
    

}
