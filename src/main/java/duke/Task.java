package duke;
/**
 * Task class is the basic type of task. It is not declared as abstract as a
 * user can still create a task that is not todo, not deadline and not events.
 * It has only the description and whether it is marked as done or undone
 *
 * @author Muhammad Reyaaz 
 * @version %I% %G%
 * @since 11
 */
class Task {
    
    private final boolean isDone; 
    private final String description;
    /**
    * Sole constructor. (For invocation by subclass
    * constructors, typically implicit)
    */
    protected Task() {
        isDone = false;
        description = "";
    }
    /**
     * All tasks initally created by the user is marked as undone. So, the
     * entire line will just be treated as the task's title
     *
     * @param description Title of the task
     */
    Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    /**
     * Tasks that are marked as done or undone will be required to return a
     * new task due to immutability of the Task
     *
     * @param description Title of the task
     * @param isDone Whether the task is marked as done 
     */
    Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }
    /**
     * Due to immutability, a new Task is returned once the user marks the
     * task as done
     *
     * @return Task 
     */
    Task markAsDone() {
        return new Task(description, true);
    }
    /**
     * Due to immutability, a new Task is returned once the user marks the
     * task as undone
     *
     * @return Task 
     */
    Task markAsUndone() {
        return new Task(description, false);
    }
    /**
     * Check if the task is marked as done.
     *
     * @return isDone True if task is marked as done
     */
    public String getStatusIcon() {
        return (isDone ? Parser.MARK_SYMBOL : " ");
    }
    /**
     * Retrieve the title of the task that has been keyed in by the user
     * previously
     *
     * @return description
     */
    String getDescription() {
        return description;
    }

    @Override 
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }

}

