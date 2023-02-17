package duke.task;

/**
 * ToDo: subclass of Task
 * <p>
 * author Guo-KeCheng
 */
public class ToDo extends Task {

    /**
     * ToDo class constructor
     * Default isCompleted to be false
     *
     * @param task Task description as String
     */
    public ToDo(String task) {
        super(task, false);
    }

    /**
     * ToDo class constructor
     *
     * @param task        Task description as String
     * @param isCompleted Completion status. True -> Completed. False -> Not Completed
     */
    public ToDo(String task, boolean isCompleted) {
        super(task, isCompleted);
    }

    /**
     * Get the String representation of the task type
     *
     * @return String representation of the task type
     */
    @Override
    public String getTaskType() {
        return "Todo";
    }

    /**
     * Get the String representation of the completion status
     *
     * @return String representation of the completion status
     */
    @Override
    public String getStatus() {
        return String.format("%s", isCompleted());
    }

    /**
     * Get the String representation of the task description
     *
     * @return String representation of the task description
     */
    @Override
    public String getDescription() {
        return getTask();
    }

    /**
     * Override toString method
     *
     * @return String representation of task object including completion status
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public int compareTo(Task anotherTask) {
        if (anotherTask instanceof ToDo) {
            return -1;
        } else if (anotherTask instanceof Deadline) {
            return 1;
        } else if (anotherTask instanceof Event) {
            return 1;
        } else {
            return 0; // never reach here
        }
    }

}
