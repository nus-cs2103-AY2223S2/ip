package duke;

import java.util.ArrayList;

/**
 * This abstract class is a Task. A task contains
 * the description of the task and whether the task is completed.
 */
public abstract class Task {

    private final String description;
    private boolean isDone;
    private ArrayList<String> tags;

    /**
     * Constructs a task with the given description.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.tags = new ArrayList<>();
    }

    /**
     * Constructs a task with the given description and isDone.
     *
     * @param description Description of the deadline task.
     * @param isDone      Whether the deadline task is done.
     */
    public Task(String description, boolean isDone, ArrayList<String> tags) {
        this.description = description;
        this.isDone = isDone;
        this.tags = tags;
    }

    public void addTag(String tagToAdd) {
        tags.add(tagToAdd);
    }

    public String getStringOfTags() {
        String tagsString = "";
        for (String tag : tags) {
            tagsString += tag + " ";
        }
        return tagsString;
    }

    public ArrayList<String> getTags() {
        return tags;
    }
    public abstract String convertToStorableString();

    public boolean isDone() {
        return this.isDone;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    public String toString() {
        return (isDone ? "[X] " : "[] ") + this.description;
    }

}
