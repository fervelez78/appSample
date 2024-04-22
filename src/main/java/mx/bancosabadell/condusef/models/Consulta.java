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
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class Consulta {

   
    @NotBlank(message = "InstitucionClave no puede estar vacío")
    @Size(max = 400, message = "La longitud máxima de InstitucionClave es 400")
    private String institucionClave;

    @NotBlank(message = "Sector no puede estar vacío")
    @Size(max = 200, message = "La longitud máxima de Sector es 200")
    private String sector;

    @NotNull(message = "ConsultasTrim no puede ser nulo")
    @Digits(integer = 1, fraction = 0, message = "ConsultasTrim debe ser un número entero de un dígito")
    @Min(value = 1, message = "ConsultasTrim debe ser mayor o igual a 1")
    @Max(value = 4, message = "ConsultasTrim debe ser menor o igual a 4")
    private Integer consultasTrim;

    @NotNull(message = "NumConsultas no puede ser nulo")
    @Digits(integer = 1, fraction = 0, message = "NumConsultas debe ser un número entero de un dígito")
    @Min(value = 1, message = "NumConsultas debe ser mayor o igual a 1")
    @Max(value = 1, message = "NumConsultas debe ser igual a 1")
    private Integer numConsultas;

    @NotBlank(message = "ConsultasFolio no puede estar vacío")
    @Size(max = 50, message = "La longitud máxima de ConsultasFolio es 50")
    private String consultasFolio;

    @NotNull(message = "ConsultasEstatusCon no puede ser nulo")
    @Digits(integer = 1, fraction = 0, message = "ConsultasEstatusCon debe ser un número entero de un dígito")
    @Min(value = 1, message = "ConsultasEstatusCon debe ser 1 o 2")
    @Max(value = 2, message = "ConsultasEstatusCon debe ser 1 o 2")
    private Integer consultasEstatusCon;

    @NotBlank(message = "ConsultasFecAten no puede estar vacío")
    @Pattern(regexp = "\\d{2}/\\d{2}/\\d{4}", message = "El formato de ConsultasFecAten debe ser dd/MM/yyyy")
    private String consultasFecAten;

    @NotNull(message = "EstadosId no puede ser nulo")
    @Digits(integer = 2, fraction = 0, message = "EstadosId debe ser un número entero de dos dígitos")
    @Min(value = 1, message = "EstadosId debe ser mayor o igual a 1")
    @Max(value = 32, message = "EstadosId debe ser menor o igual a 32")
    private Integer estadosId;

    @NotBlank(message = "ConsultasFecRecepcion no puede estar vacío")
    @Pattern(regexp = "\\d{2}/\\d{2}/\\d{4}", message = "El formato de ConsultasFecRecepcion debe ser dd/MM/yyyy")
    private String consultasFecRecepcion;

    @NotNull(message = "MediosId no puede ser nulo")
    @Digits(integer = 2, fraction = 0, message = "MediosId debe ser un número entero de dos dígitos")
    private Integer mediosId;

    @NotBlank(message = "Producto no puede estar vacío")
    @Size(max = 12, message = "La longitud máxima de Producto es 12")
    private String producto;

    @NotBlank(message = "CausaId no puede estar vacío")
    @Size(max = 4, message = "La longitud máxima de CausaId es 4")
    private String causaId;

    @NotNull(message = "ConsultasCP no puede ser nulo")
    @Digits(integer = 10, fraction = 0, message = "ConsultasCP debe ser un número entero de hasta 10 dígitos")
    @Min(value = 1, message = "ConsultasCP debe ser mayor o igual a 1")
    @Max(value = 9999999999L, message = "ConsultasCP debe ser menor o igual a 9999999999")
    private Long consultasCP;

    @NotNull(message = "ConsultasMpioId no puede ser nulo")
    @Digits(integer = 8, fraction = 0, message = "ConsultasMpioId debe ser un número entero de hasta 8 dígitos")
    @Min(value = 1, message = "ConsultasMpioId debe ser mayor o igual a 1")
    @Max(value = 99999999, message = "ConsultasMpioId debe ser menor o igual a 99999999")
    private Integer consultasMpioId;

    @Digits(integer = 8, fraction = 0, message = "ConsultasLocId debe ser un número entero de hasta 8 dígitos")
    @Max(value = 99999999, message = "ConsultasLocId debe ser menor o igual a 99999999")
    private Integer consultasLocId;

    @Digits(integer = 8, fraction = 0, message = "ConsultasColId debe ser un número entero de hasta 8 dígitos")
    @Max(value = 99999999, message = "ConsultasColId debe ser menor o igual a 99999999")
    private Integer consultasColId;

    @NotNull(message = "ConsultascatnivelatenId no puede ser nulo")
    @Digits(integer = 2, fraction = 0, message = "ConsultascatnivelatenId debe ser un número entero de dos dígitos")
    private Integer consultascatnivelatenId;

    @NotBlank(message = "ConsultasPori no puede estar vacío")
    @Pattern(regexp = "SI|NO", message = "ConsultasPori debe ser 'SI' o 'NO'")
    private String consultasPori;
}

