package com.esfm.extension.api;

import java.io.Serial;

public class ApiException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = -5885155226898287919L;
    private IErrorCode errorCode;

    public ApiException(IErrorCode errorCode) {
        super(errorCode.getMsg());
        this.errorCode = errorCode;
    }

    public ApiException(String message) {
        super(message);
    }

    public ApiException(Throwable cause) {
        super(cause);
    }

    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public IErrorCode getErrorCode() {
        return this.errorCode;
    }
}