package duke.task;

public class ToDo extends Task {

    public ToDo(String name, boolean isDone) {
        super(name, isDone);
    }

    public ToDo(String name) {
        this(name, false);
    }

    @Override
    protected String getTaskType() {
        return "T";
    }
}
