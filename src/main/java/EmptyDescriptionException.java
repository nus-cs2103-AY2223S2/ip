public class EmptyDescriptionException extends TwoFiveException {
    private String taskType;

    public EmptyDescriptionException(String taskType) {
        this.taskType = taskType;
    }

    @Override
    public String getMessage() {
        return "☹ OOPS!!! The description of a " + taskType + " cannot be empty.";
    }
}
