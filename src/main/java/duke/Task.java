package duke;

/**
 * Class that defines the Task object
 */
public class Task {
    /** Whether the task is completed or not */
    protected boolean isCompleted = false;
    /** Description of the task */
    protected String title;
    /** Type of Task */
    protected char taskType; // can be T, D or E

    /**
     * Default constructor for Task objects
     *
     * @param title specifies the description of this task object
     * @param taskType specifies whether the task is a deadline, todo or event
     */
    public Task(boolean isCompleted, String title, char taskType) {
        this.isCompleted = isCompleted;
        this.title = title;
        this.taskType = taskType;
    }

    // Methods:
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
