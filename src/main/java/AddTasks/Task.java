package AddTasks;

import java.util.ArrayList;

public class Task {

    private static ArrayList<Task> tasks = new ArrayList<>();
    public String description;
    protected boolean isDone;

    /**
     * Constructor for Task object.
     * @param description Task object's description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Prints "X" if task's isDone is true, else print " ".
     * @return A String "X" or " " depending on the boolean value isDone.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Sets the boolean value of isDone to true
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Sets the boolean value of isDone to false
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Prints accordingly depending on the String input.
     * Changes the task object's value of isDone according to the string input.
     * @param str The String word to be read from.
     */
    public String marking(String str) {
        // converting string to integer
        if (str.equals("mark")) {
            this.markAsDone();
            return "Okay! I've marked this task as done:" + "\n" + this;
        } else {
            this.markAsUndone();
            return "Aites cool, I've marked this task as not done yet:" + "\n" + this;
        }
    }

    /**
     * Returns a string representation of the task object.
     *
     * @return String representation of the task object.
     */
    public String toString() {
        return this.description;
    }
}