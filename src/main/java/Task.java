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

    /**
     *Constructor for invalid input that throws general DukeException
     *
     * @throws DukeException
     */
    public Task() throws DukeException {
        throw new DukeException();
    }

    // Methods:
    public String toString() {
        if (this.isCompleted) {
            return "[" + taskType + "]" + " [X] " + title;
        }
        return "[" + taskType + "]" + " [ ] " + title;
    }

    public void setCompleted(boolean setting) {
        this.isCompleted = setting;
        if (setting) {
            System.out.println("Nice! I've marked this task as completed:");
            System.out.println(" " + this.toString() + "\n");
        }
        else {
            System.out.println("OK, I've marked this task as incomplete:");
            System.out.println(" " + this.toString() + "\n");
        }
    }

    public String encode() {
        if (this.isCompleted) {
            return taskType + " | " + "X" + " | " + this.title;
        }
        else {
            return taskType + " | " + " " + " | " + this.title;
        }
    }
}
