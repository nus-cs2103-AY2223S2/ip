package fideline.exception;

public class DataFileNotFoundException extends FidelineException {
    public DataFileNotFoundException() {
        super("i couldn't find an existing data file!");
    }
}
