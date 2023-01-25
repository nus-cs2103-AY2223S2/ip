package tasks;

import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Task {

    protected String description;
    protected boolean isDone;
    protected DateTimeFormatter inputDateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    protected DateTimeFormatter outputDateTimeFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm");

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void setDone(boolean done) {
        this.isDone = done;
    }

    /**
     * Check if a task contains a given string.
     *
     * @param toMatch String to check for.
     * @return Whether the task contains the given string or not.
     */
    public boolean match(String toMatch) {
        Pattern pattern = Pattern.compile(toMatch);
        Matcher matcher = pattern.matcher(this.description);
        return matcher.find();
    }

    public abstract String toEncodedString();

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "]" + " " + this.description;
    }
}
