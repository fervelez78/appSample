package mx.bancosabadell.condusef.models;

import java.util.List;

import lombok.Data;
@Data
public class ResponseRedeco {
    private List<String> addedRows;
    private List<ErrorInfoResponse> errors;
    private List<InfoValidate> errorsValidate;
    private List<Queja> Quejas;

}
