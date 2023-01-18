public class TaskUndoneException extends TwoFiveException {
    @Override
    public String getMessage() {
        return "☹ OOPS!!! This task has not been done yet.";
    }
}
