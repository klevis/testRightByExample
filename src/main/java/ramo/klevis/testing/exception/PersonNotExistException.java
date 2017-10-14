package ramo.klevis.testing.exception;

/**
 * Created by klevis.ramo on 10/12/2017.
 */
public class PersonNotExistException extends RuntimeException {
    public PersonNotExistException(String message) {
        super(message);
    }
}
