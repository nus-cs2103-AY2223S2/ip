public class TaskNumRangeException extends DuduException {
    public TaskNumRangeException(String msg) {
        super(msg);
    }
    @Override
    public String toString() {
        return "The task number is not in the range.";
    }
}
