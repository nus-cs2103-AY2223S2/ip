public class EmptyStartTimeException extends TwoFiveException {
    @Override
    public String getMessage() {
        return "☹ OOPS!!! The start time of a event cannot be empty.";
    }
}
