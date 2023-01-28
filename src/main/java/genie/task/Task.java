package genie.task;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }
    public void markDone() {
        this.isDone = true;
    }
    public void unmarkDone() {
        this.isDone = false;
    }
    @Override
    public String toString() {
        return this.getStatusBox() + this.description;
    }
    public String getStatusBox() {
        return "[" +  this.getStatusIcon() + "] ";
    }
    public String toFileFormat() {
        return this.getStatusBox() + this.description;
    }
    public boolean contains(String keyword) {
        return description.contains(keyword);
    }
    public String getDescription() {
        return description;
    }
    public boolean containsWord(String keyword) {
        Pattern pattern = Pattern.compile("\\b" + keyword + "\\b", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(description);
        return matcher.find();
    }
}

