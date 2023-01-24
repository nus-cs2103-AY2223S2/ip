package dukes.task;

public class ToDo extends Task {

    public ToDo(String taskName) {
        super(taskName);
        this.tag = "T";
    }

    public ToDo(String taskName, boolean isDone) {
        super(taskName, isDone);
        this.tag = "T";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
