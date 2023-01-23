package duke.tasks;
/**
 * Represents Task. A <code>ITask</code> abstract class corresponds to
 * the task
 */
public abstract class ITask {
    /**
     * TaskTypes represent different type of task
     */
    public enum TaskTypes {
        ToDos,
        Deadlines,
        Events,
        Unknown

    }

    /**
     * To Convert String command to enum TaskTypes
     *
     * @param cmd String command
     */
    public static ITask.TaskTypes convertTaskTypeCmdToEnum(String cmd) {
        switch (cmd) {
            case "todo":
                return TaskTypes.ToDos;
            case "deadline":
                return TaskTypes.Deadlines;
            case "event":
                return TaskTypes.Events;
        }
        return TaskTypes.Unknown;
    }


    private final String description;

    /**
     * To mark the task as done
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * To mark the task as undone
     */
    public void markAsUnDone() {
        isDone = false;
    }

    private boolean isDone;

    /**
     * Constructor for ITask
     *
     * @param description the description of main task
     */
    public ITask(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructor for ITask
     *
     * @param description the description of main task
     * @param isDone      the status of main task
     */
    public ITask(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * To convert the task into string for export the local file
     */
    abstract public String toSaveFormat();

    /**
     * To convert the task status to a String icon representation
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;

    }
}