public class EmptyTasknumException extends TwoFiveException {
    @Override
    public String getMessage() {
        return ":( OOPS!!! The task number cannot be empty.";
    }
}
