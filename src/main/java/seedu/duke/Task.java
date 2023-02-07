package seedu.duke;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Task {

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
        String status = " ";
        if (isDone) {
            status = "X";
        }
        String response = "[" + abbreviation + "][" + status + "] " + task;
        return response.trim();
    }

    public Boolean contains(String search) {
        Pattern pattern = Pattern.compile(search);
        Matcher matcher = pattern.matcher(task);
        return matcher.find();
    }

    public void addTag(Tag tag) {
        this.tags.add(tag);
    }

    public abstract String getBreakdown();
}
