package fideline.exception;

/**
 * Informs user that the data file cannot be found.
 *
 * @author Fun Leon
 */
public class DataFileNotFoundException extends FidelineException {
    public DataFileNotFoundException() {
        super("i couldn't find an existing data file!");
    }
}
