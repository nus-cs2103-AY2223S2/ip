package task;

/**
 * Class for a Todo Task.
 */
public class TodoTask extends Task{
    /**
     * Constructor for todo Task.
     *
     * @param name Title/name of Task.
     */
    public TodoTask(String name) {
        super(name);
  }

    /**\
     * Constructor for todo Task when loaded in from hard drive.
     *
     * @param name Title/name of task.
     * @param isDone True if task has been marked as done, false otherwise.
     */
    public TodoTask(String name, Boolean isDone) {
        super(name, isDone);
  }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}
