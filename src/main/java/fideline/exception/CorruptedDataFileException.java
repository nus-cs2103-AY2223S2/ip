package fideline.exception;

/**
 * Informs user that the data file cannot be read. File has to be
 * restored or deleted for program to run.
 *
 * @author Fun Leon
 */
public class CorruptedDataFileException extends FidelineException {

    public CorruptedDataFileException() {
        super("data file has been corrupted!");
    }
}
