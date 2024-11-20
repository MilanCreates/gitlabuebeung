package domain;

public class InvalidValueException extends RuntimeException {
    public InvalidValueException(String s) {
        super(s);
    }
}
