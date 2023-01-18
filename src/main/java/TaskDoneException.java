public class TaskDoneException extends TwoFiveException {
    @Override
    public String getMessage() {
        return "☹ OOPS!!! This task has already been done.";
    }
}
