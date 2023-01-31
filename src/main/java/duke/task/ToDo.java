package duke.task;

/**
 * A task without any associated times
 */
@TaskInfo(type = "T")
public class ToDo extends Task {
    public ToDo(String task) {
        super(task);
    }
}
