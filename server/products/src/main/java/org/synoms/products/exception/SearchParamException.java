package org.synoms.products.exception;


public class SearchParamException extends RuntimeException{
    public SearchParamException() {
        super();
    }

    public SearchParamException(String message) {
        super(message);
    }

    public SearchParamException(String message, Throwable cause) {
        super(message, cause);
    }

    public SearchParamException(Throwable cause) {
        super(cause);
    }

    protected SearchParamException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
