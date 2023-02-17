package duke.task;

import java.util.regex.Pattern;

/**
 * Represents over-arching Task type.
 */
public class Task {
    protected String title;
    protected String type;
    protected boolean done;
    private final String CHECKED = "[X]";
    private final String UNCHECKED = "[ ]";


    /**
     * Constructor Method for Task.
     *
     * @param title title of the Task.
     */
    public Task(String title) {
        this.title = title;
        this.done = false;
        this.type = "";
    }

    /**
     * Method for marking the task.
     */
    public void mark() {
        this.done = true;
    }

    /**
     * Method for unmarking the task.
     */
    public void unmark() {
        this.done = false;
    }

    /**
     * Method to get the status of the task.
     *
     * @return boolean representing the status of the task.
     */
    public boolean isMarked() {
        return this.done;
    }

    /**
     * Method for checking if a substring exists in the title.
     *
     * @param subString to search for.
     * @return boolean representing result of the search.
     */
    public boolean contains(String subString) {
        //Add support for searching case-insensitive search.
        return Pattern.compile(Pattern.quote(subString), Pattern.CASE_INSENSITIVE).matcher(this.title).find();
        //return this.title.contains(subString);
    }

    /**
     * Alternative toString method purely for writing to hard drive.
     * Can also be used for testing.
     *
     * @return String representation of the task with all attribute information.
     */
    public String toData() {
        return this.type + " | " + (this.done ? "1" : "0") + " | " + this.title;
    }

    /**
     * Method for getting String representation of Deadline.
     *
     * @return String representation of the deadline.
     */
    @Override
    public String toString() {
        return this.type + (done ? CHECKED : UNCHECKED) + " " + this.title;
    }

}
