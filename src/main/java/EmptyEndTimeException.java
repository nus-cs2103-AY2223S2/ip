public class EmptyEndTimeException extends TwoFiveException {
    @Override
    public String getMessage() {
        return "☹ OOPS!!! The end time of a event cannot be empty.";
    }
}
