package duke.tasktypes;

/**
 * Class which has all the basic fields of a task.
 */
public class Task {
    protected String name;
    protected boolean isDone;
    protected String tag = "#Untagged";

    /**
     * Constructor to initialize a Task.
     *
     * @param taskName String which will be the name of the task.
     */
    Task(String taskName) {
        this.name = taskName;
        this.isDone = false;
    }

    public void setDone() {
        this.isDone = true;
    }

    public void setUndone() {
        this.isDone = false;
    }

    public String getName() {
        return this.name;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public String getTag() {
        return this.tag;
    }

    public void setTag(String newTag) {
        this.tag = newTag;
    }

}
