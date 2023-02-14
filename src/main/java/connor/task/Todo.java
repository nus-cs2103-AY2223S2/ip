package connor.task;

/**
 * Todo object to keep track of a task that has no time constraint.
 */
public class Todo extends Task {

    /**
     * Constructor to instantiate a new Todo object with a taskName.
     *
     * @param taskName name of the task.
     */
    public Todo(String taskName) {
        super(taskName);
        this.taskName = taskName;
    }

    /**
     * Constructor to instantiate a Todo object read from the memory.
     *
     * @param taskName name of the task.
     * @param isDone indicates if task is done.
     */
    public Todo(String taskName, Boolean isDone) {
        super(taskName, isDone);
        this.taskName = taskName;
    }

    /**
     * Returns a String in a format that is meant to be stored in the memory.
     *
     * @return String that represents the Task instance in the memory.
     */
    @Override
    public String dataFormat() {
        return "T|" + super.dataFormat();
    }

    @Override
    public int compareTo(Task task) {
        if (task instanceof Deadline) {
            return -1;
        } else if (task instanceof Event) {
            return -1;
        }
        return this.taskName.compareTo(task.taskName);
    }

    /**
     * Returns a String which is a concatenation of task type, if the task is done and taskName.
     *
     * @return String representation of the task to be printed.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
