package ramo.klevis.testing.exception;

/**
 * Created by klevis.ramo on 10/12/2017.
 */
public class PersonRequiredFieldsMissingException extends RuntimeException {
    public PersonRequiredFieldsMissingException(String message) {
        super(message);
    }
}
