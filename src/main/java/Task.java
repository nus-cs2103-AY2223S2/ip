/**
 * Class that defines the Task object
 */
public class Task {
    /** Whether the task is completed or not */
    protected boolean isCompleted = false;
    /** Description of the task */
    protected String title;

    /**
     * Default constructor for Task objects
     *
     * @param title specifies the description of this task object
     */
    public Task(String title) {
        this.isCompleted = false;
        this.title = title;
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
            return "[X] " + title;
        }
        return "[ ] " + title;
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
}
