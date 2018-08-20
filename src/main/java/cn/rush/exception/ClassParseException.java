package cn.rush.exception;

public class ClassParseException extends RuntimeException {
    public ClassParseException(String message) {
        super(message);
    }

    public ClassParseException(String message, Throwable cause) {
        super(message, cause);
    }


}
