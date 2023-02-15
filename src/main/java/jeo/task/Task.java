package jeo.task;

/**
 * Represents a generic task.
 * @author Goh Jun How
 * @version 0.3
 */
public abstract class Task {
    protected String description;
    protected String tags;
    protected boolean isDone;

    /**
     * Acts as the constructor taking in the task description, to be inherited.
     * @param description String describing the task.
     */
    public Task(String description, String tags) {
        this.description = description;
        this.tags = tags;
        this.isDone = false;
    }

    /**
     * Gets the task description.
     * @return String representing the task description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the unformatted tags.
     * @return String representing the unformatted tags.
     */
    public String getTags() {
        return tags;
    }

    /**
     * Gets the tags in String representation.
     * @return String representing the tags.
     */
    public String getFormattedTags() {
        if (tags.equals("")) {
            return "";
        }
        String[] tagList = tags.split("\\\\");
        StringBuilder sb = new StringBuilder();
        for (String tag : tagList) {
            sb.append("[#").append(tag).append("]");
        }
        return sb.toString();
    }

    /**
     * Gets the status icon.
     * @return String representing either "X" or " ".
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done jeo.task with X
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void unmarkFromDone() {
        isDone = false;
    }

    /**
     * Gets the string representation of a task.
     * @return String representing a task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "]" + getFormattedTags() + " " + getDescription();
    }
}
