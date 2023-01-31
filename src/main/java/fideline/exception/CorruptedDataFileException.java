package fideline.exception;

public class CorruptedDataFileException extends FidelineException {
    public CorruptedDataFileException() {
        super("data file has been corrupted!");
    }
}
