public class EmptyDateException extends TwoFiveException {
    @Override
    public String getMessage() {
        return ":( OOPS!!! The due date cannot be empty.";
    }
}
