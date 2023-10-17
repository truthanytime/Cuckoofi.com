package com.cuckoofi.gatewayservice.exception.model;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ExceptionResponseModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private String errCode;
    private String err;
    private String errDetails;
    private Object object;
    private Date date;

    public ExceptionResponseModel(String errCode, String err, String errDetails, Object object, Date date) {
        this.errCode = errCode;
        this.err = err;
        this.errDetails = errDetails;
        this.object = object;
        this.date = date;
    }
}
