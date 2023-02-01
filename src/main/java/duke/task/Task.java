package duke.task;

/**
 * Task that the user can add to the TaskList to be done.
 * Parent class of ToDo, Deadline, Event.
 */
public abstract class Task {
    private boolean isDone;
    private String task;

    /**
     * Class constructor of Task.
     * @param title the title of the Task.
     */
    public Task(String title) {
        this.task = title;
    }

    /**
     * Marks the state of the Task as done.
     * @param needPrint the indicator that shows whether the message needs to be printed
     */
    public void markDone(boolean needPrint) {
        this.isDone = true;
        if (needPrint) {
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(this.toString());
        }
    }

    /**
     * Mark the state of the Task as not done yet.
     * @param needPrint the indicator that shows whether the message needs to be printed
     */
    public void unmark(boolean needPrint) {
        this.isDone = false;
        if (needPrint) {
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(this.toString());
        }
    }

    /**
     * Returns the state of the Task.
     * @return the state of the Task
     */
    public boolean checkDone() {
        return this.isDone;
    }

    /**
     * Returns this Task.
     * @return this Task
     */
    public String getTask() {
        return this.task;
    }

    /**
     * Stringifies the Task.
     * @return the string representation of the Task
     */
    public String toString() {
        String mark = this.isDone ? "X" : " ";
        return "[" + mark + "] " + this.task;
    }

    /**
     * Stringifies the Task with relevant format for the saved data file.
     * @return the string representation of the Task that follows the format for the saved data file
     */
    public String toTxtString() {
        String mark2 = this.isDone ? "1" : "0";
        return "|" + mark2 + "|" + this.task;
    }
}
