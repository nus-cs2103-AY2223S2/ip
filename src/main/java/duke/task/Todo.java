package duke.task;

import duke.task.Task;

public class Todo extends Task {

    public Todo(String taskName) {
        super(taskName);
    }

    public Todo(String taskName, boolean isDone) {
        super(taskName);
        this.isDone = isDone;
    }
    @Override
    public String[] encode() {
        String[] res = new String[]{"T", this.getStatusIcon(), this.taskName};
        return res;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}