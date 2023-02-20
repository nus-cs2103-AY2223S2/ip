package duke.task;

/**
 * Task that the user can add to the TaskList to be done.
 * Parent class of ToDo, Deadline, Event.
 */
public abstract class Task {
    private boolean isDone;
    private String title;

    /**
     * Class constructor of Task.
     * @param title the title of the Task.
     */
    public Task(String title) {
        this.title = title;
    }

    /**
     * Marks the state of the Task as done.
     */
    public String markDone() {
        String res = "";
        this.isDone = true;
        res += "Nice! I've marked this task as done:\n";
        res += this.toString();
        return res;
    }

    /**
     * Mark the state of the Task as not done yet.
     */
    public String unmark() {
        String res = "";
        this.isDone = false;
        res += "OK, I've marked this task as not done yet:\n";
        res += this.toString();
        return res;
    }

    /**
     * Returns this Task.
     * @return this Task
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Stringifies the Task.
     * @return the string representation of the Task
     */
    public String toString() {
        String mark = this.isDone ? "X" : " ";
        return "[" + mark + "] " + this.title;
    }

    /**
     * Stringifies the Task with relevant format for the saved data file.
     * @return the string representation of the Task that follows the format for the saved data file
     */
    public String toTxtString() {
        String mark2 = this.isDone ? "1" : "0";
        return "|" + mark2 + "|" + this.title;
    }
}
