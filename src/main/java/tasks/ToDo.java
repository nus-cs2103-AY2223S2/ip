package tasks;

import tasks.Task;
import tasks.TaskType;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toEncodedString() {
        return "[" + TaskType.T + "]" + super.toString();
    }

    @Override
    public String toString() {
        return "[" + TaskType.T + "]" + super.toString();
    }
}
