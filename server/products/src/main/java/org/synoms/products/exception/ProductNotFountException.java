package org.synoms.products.exception;

public class ProductNotFountException extends RuntimeException{
    public ProductNotFountException() {
        super();
    }

    public ProductNotFountException(String message) {
        super(message);
    }

    public ProductNotFountException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductNotFountException(Throwable cause) {
        super(cause);
    }

    protected ProductNotFountException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
