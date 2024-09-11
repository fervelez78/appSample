package mx.bancosabadell.condusef.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class Aclaracion {
	
	@NotBlank(message = "AclaracionDenominacion no puede estar vacío")
	@Size(max = 400, message = "La longitud máxima de AclaracionDenominacion es 400")
	private String aclaracionDenominacion;
	
	@NotBlank(message = "AclaracionSector no puede estar vacío")
	@Size(max = 200, message = "La longitud máxima de AclaracionSector es 200")
	private String aclaracionSector;
	
	@NotBlank(message = "AclaracionTrimestre no puede estar vacío")
	@Size(max = 1, message = "La longitud máxima de AclaracionTrimestre es 1")
	private Integer aclaracionTrimestre;
	
	@NotBlank(message = "AclaracionNumero no puede estar vacío")
	@Size(max = 1, message = "La longitud máxima de AclaracionNumero es 1")
	private Integer aclaracionNumero;
	
	@NotBlank(message = "AclaracionFolioAtencion no puede estar vacío")
	@Size(max = 50, message = "La longitud máxima de AclaracionFolioAtencion es 50")
	private String aclaracionFolioAtencion;
	
	@NotBlank(message = "AclaracionEstadoConPend no puede estar vacío")
	@Size(max = 1, message = "La longitud máxima de AclaracionEstadoConPend es 1")
	private Integer aclaracionEstadoConPend;
	
	@NotBlank(message = "AclaracionFechaAclaracion no puede estar vacío")
	@Pattern(regexp = "\\d{2}/\\d{2}/\\d{4}", message = "El formato de ConsultasFecRecepcion debe ser dd/MM/yyyy")
	private String aclaracionFechaAclaracion;
	
	@NotBlank(message = "AclaracionFechaAtencion no puede estar vacío")
	@Pattern(regexp = "\\d{2}/\\d{2}/\\d{4}", message = "El formato de ConsultasFecRecepcion debe ser dd/MM/yyyy")
	private String aclaracionFechaAtencion;
	
	@NotBlank(message = "AclaracionMedioRecepcionCanal no puede estar vacío")
	@Size(max = 2, message = "La longitud máxima de AclaracionMedioRecepcionCanal es 2")
	private Integer aclaracionMedioRecepcionCanal;
	
	@NotBlank(message = "AclaracionProductoServicio no puede estar vacío")
	@Size(max = 12, message = "La longitud máxima de AclaracionProductoServicio es 12")
	private String aclaracionProductoServicio;
	
	@NotBlank(message = "AclaracionCausaMotivo no puede estar vacío")
	@Size(max = 4, message = "La longitud máxima de AclaracionCausaMotivo es 4")
	private String aclaracionCausaMotivo;
	
	@NotBlank(message = "AclaracionFechaResolucion no puede estar vacío")
	@Pattern(regexp = "\\d{2}/\\d{2}/\\d{4}", message = "El formato de ConsultasFecRecepcion debe ser dd/MM/yyyy")
	private String aclaracionFechaResolucion;
	
	@NotBlank(message = "AclaracionFechaNotifiUsuario no puede estar vacío")
	@Pattern(regexp = "\\d{2}/\\d{2}/\\d{4}", message = "El formato de ConsultasFecRecepcion debe ser dd/MM/yyyy")
	private String aclaracionFechaNotifiUsuario;
	
	@NotBlank(message = "AclaracionEntidadFederativa no puede estar vacío")
	@Size(max = 2, message = "La longitud máxima de AclaracionEntidadFederativa es 2")
	private Integer aclaracionEntidadFederativa;
	
	@NotBlank(message = "AclaracionCodigoPostal no puede estar vacío")
	@Size(max = 10, message = "La longitud máxima de AclaracionCodigoPostal es 10")
	private Integer aclaracionCodigoPostal;
	
	@NotBlank(message = "AclaracionMunicipioAlcaldia no puede estar vacío")
	@Size(max = 8, message = "La longitud máxima de AclaracionMunicipioAlcaldia es 8")
	private Integer aclaracionMunicipioAlcaldia;
	
	@NotBlank(message = "AclaracionLocalidad no puede estar vacío")
	@Size(max = 8, message = "La longitud máxima de AclaracionLocalidad es 8")
	private Integer aclaracionLocalidad;
	
	@NotBlank(message = "AclaracionColonia no puede estar vacío")
	@Size(max = 8, message = "La longitud máxima de AclaracionColonia es 8")
	private Integer aclaracionColonia;
	
	@NotBlank(message = "AclaracionMonetario no puede estar vacío")
	@Size(max = 2, message = "La longitud máxima de AclaracionMonetario es 2")
	private String aclaracionMonetario;
	
	@NotBlank(message = "AclaracionMontoReclamado no puede estar vacío")
	@Size(max = 13, message = "La longitud máxima de AclaracionMontoReclamado es 13")
	private Integer aclaracionMontoReclamado;
	
	@NotBlank(message = "AclaracionPori no puede estar vacío")
	@Size(max = 2, message = "La longitud máxima de AclaracionPori es 2")
	private String aclaracionPori;
	
	@NotBlank(message = "AclaracionTipoPersona no puede estar vacío")
	@Size(max = 1, message = "La longitud máxima de AclaracionTipoPersona es 1")
	private Integer aclaracionTipoPersona;
	
	@NotBlank(message = "AclaracionSexo no puede estar vacío")
	@Size(max = 1, message = "La longitud máxima de AclaracionSexo es 1")
	private String aclaracionSexo;
	
	@NotBlank(message = "AclaracionEdad no puede estar vacío")
	@Size(max = 3, message = "La longitud máxima de AclaracionEdad es 3")
	private Integer aclaracionEdad;
	
	@NotBlank(message = "AclaracionNivelAtencion no puede estar vacío")
	@Size(max = 2, message = "La longitud máxima de AclaracionNivelAtencion es 2")
	private Integer aclaracionNivelAtencion;
	
	@NotBlank(message = "AclaracionFolioCondusef no puede estar vacío")
	@Size(max = 50, message = "La longitud máxima de AclaracionFolioCondusef es 50")
	private String aclaracionFolioCondusef;
	
	@NotBlank(message = "AclaracionReversa no puede estar vacío")
	@Size(max = 1, message = "La longitud máxima de AclaracionReversa es 1")
	private Integer aclaracionReversa;
	
	@NotBlank(message = "AclaracionOperacionExtranjero no puede estar vacío")
	@Size(max = 2, message = "La longitud máxima de AclaracionOperacionExtranjero es 2")
	private String aclaracionOperacionExtranjero;
}
