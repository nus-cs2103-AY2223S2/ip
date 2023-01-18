public class EmptyDeadlineException extends TwoFiveException {
    @Override
    public String getMessage() {
        return "☹ OOPS!!! The deadline of a deadline cannot be empty.";
    }
}
