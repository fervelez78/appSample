package mx.bancosabadell.condusef.models;

import java.util.List;

import lombok.Data;
import mx.bancosabadell.condusef.exceptions.ErrorInfoResponse;
@Data
public class ResponseRedeco {
    private List<String> addedRows;
    private List<ErrorInfoResponse> errors;
    private List<InfoValidate> errorsValidate;
    private List<Queja> Quejas;
    private String Error;

}
