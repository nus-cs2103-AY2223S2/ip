package duke;

public class ToDo extends Task {
    private String task;

    public ToDo(String task) {
        super(task);
        this.task = task;
    }

    @Override
    public String getTask() {
        return this.task;
    }
    
    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}
