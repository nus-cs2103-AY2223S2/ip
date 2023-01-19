/**
 * The Task class.
 * Stores whatever tasks entered by the user and display them back to the user
 * when requested.
 */
public class Task {
    private final String nameOfTask;

    /**
     * The constructor for Task.
     * @param nameOfTask The name of task.
     */
    public Task(String nameOfTask) {
        this.nameOfTask = nameOfTask;
    }

    /**
     * A function to get the name of Task.
     * @return The name of Task.
     */
    public String getNameOfTask() {
        return nameOfTask;
    }
}
