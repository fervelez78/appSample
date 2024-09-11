package mx.bancosabadell.condusef.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AclaracionesInfoResponse {
	
	@JsonProperty("AclaracionDenominacion")
	private String aclaracionDenominacion;
	
	@JsonProperty("AclaracionSector")
	private String aclaracionSector;
	
	@JsonProperty("AclaracionTrimestre")
	private Integer aclaracionTrimestre;
	
	@JsonProperty("AnstitucionClave")
	private Integer aclaracionNumero;
	
	@JsonProperty("AclaracionFolioAtencion")
	private String aclaracionFolioAtencion;
	
	@JsonProperty("AclaracionEstadoConPend")
	private Integer aclaracionEstadoConPend;
	
	@JsonProperty("AclaracionFechaAclaracion")
	private String aclaracionFechaAclaracion;
	
	@JsonProperty("AclaracionFechaAtencion")
	private String aclaracionFechaAtencion;
	
	@JsonProperty("AclaracionMedioRecepcionCanal")
	private Integer aclaracionMedioRecepcionCanal;
	
	@JsonProperty("AclaracionProductoServicio")
	private String aclaracionProductoServicio;
	
	@JsonProperty("AclaracionFechaResolucion")
	private String aclaracionFechaResolucion;
	
	@JsonProperty("AclaracionFechaNotifiUsuario")
	private String aclaracionFechaNotifiUsuario;
	
	@JsonProperty("AclaracionEntidadFederativa")
	private Integer aclaracionEntidadFederativa;
	
	@JsonProperty("AclaracionCodigoPostal")
	private Integer aclaracionCodigoPostal;
	
	@JsonProperty("AclaracionMunicipioAlcaldia")
	private Integer aclaracionMunicipioAlcaldia;
	
	@JsonProperty("AclaracionLocalidad")
	private Integer aclaracionLocalidad;
	
	@JsonProperty("AclaracionColonia")
	private Integer aclaracionColonia;
	
	@JsonProperty("AclaracionMonetario")
	private String aclaracionMonetario;
	
	@JsonProperty("AclaracionMontoReclamado")
	private Integer aclaracionMontoReclamado;
	
	@JsonProperty("AclaracionPori")
	private String aclaracionPori;
	
	@JsonProperty("AclaracionTipoPersona")
	private Integer aclaracionTipoPersona;
	
	@JsonProperty("AclaracionSexo")
	private String aclaracionSexo;
	
	@JsonProperty("AclaracionEdad")
	private Integer aclaracionEdad;
	
	@JsonProperty("AclaracionNivelAtencion")
	private Integer aclaracionNivelAtencion;
	
	@JsonProperty("AclaracionFolioCondusef")
	private String aclaracionFolioCondusef;
	
	@JsonProperty("AclaracionReversa")
	private Integer aclaracionReversa;
	
	@JsonProperty("AclaracionOperacionExtranjero")
	private String aclaracionOperacionExtranjero;
	
	private List<String> errors;

}
