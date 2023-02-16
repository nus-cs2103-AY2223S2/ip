package iris.exception;

/**
 * encountered when there are necessary fields missing in the input
 */
public class MissingFieldException extends IrisException {
    public MissingFieldException(String field) {
        super(field + " field cannot be empty.");
    }
}
