package iris.exception;

/**
 * encountered when a field is entered twice
 */
public class DoubleFieldException extends IrisException {
    public DoubleFieldException(String field) {
        super(field + " field was entered more than once.");
    }
}
