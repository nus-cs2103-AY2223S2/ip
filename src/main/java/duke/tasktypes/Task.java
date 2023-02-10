package duke.tasktypes;

/**
 * Class which has all the basic fields of a task.
 */
public class Task {
    protected String name;
    protected boolean done;

    /**
     * Constructor to initialize a Task.
     *
     * @param taskName String which will be the name of the task.
     */
    Task(String taskName) {
        this.name = taskName;
        done = false;
    }

    public void setDone() {
        done = true;
    }

    public void setUndone() {
        done = false;
    }

    public String getName() {
        return this.name;
    }

}
