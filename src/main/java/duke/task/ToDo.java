package duke.task;

/**
 * A task without any associated times
 */
@TaskInfo(type = "T")
public class ToDo extends Task {
    public ToDo(String task, int priority) {
        super(task, priority);
    }

    public ToDo(String task) {
        this(task, DEFAULT_PRIORITY);
    }
}
