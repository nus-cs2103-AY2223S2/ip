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

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else if (obj.getClass() != this.getClass()) {
            return false;
        }
        ToDo task = (ToDo) obj;
        return super.equals(task);
    }
}
