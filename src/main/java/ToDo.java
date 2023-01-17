public class ToDo extends Task {
    public ToDo(String taskName, int taskNumber) {
        super(taskName, taskNumber);
    }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}
