public class EmptyDescException extends Exception {
    String taskType;
    public EmptyDescException(String taskType, String errMsg) {
        super(errMsg);
        this.taskType = taskType;
    }
    @Override
    public String toString() {
        return "☹ OOPS!!! The description of a " + this.taskType + " cannot be empty.";
    }
}
