package duke;

/**
 * Class that defines the Task object
 */
public class Task {

    protected boolean isCompleted = false;
    protected String title;
    public enum TaskType {
        T,D,E;
    }
    protected TaskType taskType;

    /**
     * Default constructor for Task objects
     *
     * @param isCompleted Boolean that specifies whether the task is completed
     * @param title String that specifies the description of this task object
     * @param taskType specifies whether the task is a deadline, todo or event
     */
    public Task(boolean isCompleted, String title, TaskType taskType) {
        this.isCompleted = isCompleted;
        this.title = title;
        this.taskType = taskType;
    }

    public String toString() {
        if (this.isCompleted) {
            return "[" + taskType + "]" + " [X] " + title;
        }
        return "[" + taskType + "]" + " [ ] " + title;
    }

    /**
     *  Sets the isCompleted to be True or False
     * @param setting boolean representing the completion status of the task
     * @return String to inform the user that the task has been marked complete/incomplete
     */
    public String setCompleted(boolean setting) {
        this.isCompleted = setting;
        String result = "";
        if (setting) {
            result += "Nice! I've marked this task as completed:\n";
        }
        else {
            result += "OK, I've marked this task as incomplete:\n";
        }
        result += " " + this.toString() + "\n";
        return result;
    }

    /**
     * Encodes a Task object into an 'easy to decode' String object
     *
     * @return String representation of a Task object, for storage in a text file
     */
    public String encode() {
        if (this.isCompleted) {
            return taskType + " | " + "X" + " | " + this.title;
        }
        else {
            return taskType + " | " + " " + " | " + this.title;
        }
    }
}
