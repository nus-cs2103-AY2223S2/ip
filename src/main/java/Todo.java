public class Todo extends Task {
    private String taskName;

    public Todo(String taskName) {
        super(taskName);
        this.taskName = taskName;
    }

    public Todo(String taskName, Boolean isDone) {
        super(taskName, isDone);
        this.taskName = taskName;
    }

    @Override
    public String dataFormat() {
        return "T|" + super.dataFormat();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
