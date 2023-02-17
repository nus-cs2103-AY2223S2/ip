package vic.tasks;

import java.util.UUID;

/**
 * Represents Task. A <code>ITask</code> abstract class corresponds to
 * the task
 */
public abstract class ITask implements Cloneable {

    private final UUID id;
    private final String description;
    private boolean isDone;


    /**
     * Constructor for ITask
     *
     * @param description the description of main task
     */
    public ITask(String description) {
        this.description = description;
        this.isDone = false;
        id = UUID.randomUUID();
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
        id = UUID.randomUUID();

    }
    public UUID getId() {
        return id;
    }
    /**
     * TaskTypes represent different type of task
     */
    public enum TaskTypes {
        ToDos,
        Deadlines,
        Events,
        Find,
        Unknown

    }

    public boolean descriptionContain(String keyword) {
        return description.contains(keyword);
    }



    /**
     * Marks the task as done
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the task as undone
     */
    public void markAsUndone() {
        isDone = false;
    }


    /**
     * Converts the task into string for export the local file
     */
    public abstract String toSaveFormat();

    /**
     * Converts the task status to a String icon representation
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }
    /**
     * Clones the object
     */
    public Object clone() throws CloneNotSupportedException {

        return super.clone();
    }
    @Override
    public boolean equals(Object obj) {
        if (getClass() != obj.getClass()) {
            return false;
        }
        ITask task = (ITask) obj;
        return task.getId() == this.getId();
    }
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;

    }
}
