package colette.exception;

/** Exception for problems that arise when running Duke */
public class ColetteException extends Exception {

    public ColetteException(String message) {
        super("I'm sorry. " + message);
    }

}
