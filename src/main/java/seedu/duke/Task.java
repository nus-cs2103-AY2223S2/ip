package seedu.duke;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents a task added by the user
 */
public abstract class Task implements Serializable {

    protected String task;
    protected Boolean isDone;
    protected Character abbreviation;
    protected ArrayList<Tag> tags;

    /**
     * Constructor for the Task Object
     * @param task
     * @throws Exception (Description of task cannot be empty)
     */
    public Task(String task) throws DukeException {
        if (task == "") {
            throw new DukeException("OOPS!!! The description of a task cannot be empty.");
        }
        this.task = task;
        this.isDone = false;
        this.tags = new ArrayList<>();
    }

    public void mark() {
        isDone = true;
    }

    public void unmark() {
        isDone = false;
    }

    /**
     * Creates a string representation of this task
     * @return String representation of this task
     */
    @Override
    public String toString() {
        String status = isDone ? "X" : " ";
        String tags = this.tags.size() > 0 ? this.tags.toString() : "";
        String response = String.format("[%s][%s] %s %s", abbreviation, status, task, tags);
        return response.trim();
    }

    /**
     * Returns true if the name of the task contains the substring
     * @param search Substring to find
     * @return true if the name of the task contains the substring
     */
    public Boolean contains(String search) {
        Pattern pattern = Pattern.compile(search);
        Matcher matcher = pattern.matcher(task);
        return matcher.find();
    }

    public void addTag(Tag tag) {
        this.tags.add(tag);
    }
}
