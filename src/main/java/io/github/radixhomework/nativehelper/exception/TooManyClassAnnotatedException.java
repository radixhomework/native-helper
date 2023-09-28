package io.github.radixhomework.nativehelper.exception;

public class TooManyClassAnnotatedException extends RuntimeException {

    public TooManyClassAnnotatedException() {
    }

    public TooManyClassAnnotatedException(String message) {
        super(message);
    }

    public TooManyClassAnnotatedException(String message, Throwable cause) {
        super(message, cause);
    }

    public TooManyClassAnnotatedException(Throwable cause) {
        super(cause);
    }

    public TooManyClassAnnotatedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
