package duke.task;
import java.util.ArrayList;
/**
 * Class that represents tasks created by the user, can be marked as done or undone
 */
public class Task {
    protected String name;
    protected String status;
    protected ArrayList<String> tags;

    /**
     * Constructor
     * @param name the name of the task
     * @param status a number that indicates the task is done if it is 1
     */
    public Task(String name, int status) {
        this.name = name;
        if (status == 1) {
            this.status = "[X]";
        } else {
            this.status = "[ ]";
        }
        this.tags = new ArrayList<String>();
    }

    /**
     * Another Constructor to handle Tasks that already have tags
     * @param name the name of the task
     * @param status a number that indicates the task is done if it is 1
     * @param tags An ArrayList of Strings representing the tags
     */
    public Task(String name, int status, ArrayList<String> tags) {
        this.name = name;
        if (status == 1) {
            this.status = "[X]";
        } else {
            this.status = "[ ]";
        }
        this.tags = tags;
    }

    /**
     * Updates a task as done.
     */
    public void mark() {
        this.status = "[X]";
    }

    /**
     * Updates a task as undone
     */
    public void unmark() {
        this.status = "[ ]";
    }

    /**
     * Adds a tag to the Task
     * @param tag A String representing the tag to be added.
     */
    public void addTag(String tag) {
        this.tags.add(tag);
    }
    /**
     * Formats Task message to facilitate updating local tasks
     * @return a String to be written to the local hard disk
     */
    public String toStoreFormatString() {
        StringBuilder result = new StringBuilder();
        for (String tag : tags) {
            result.append("/").append(tag);
        }
        return result.toString();
    }

    /**
     * Displays all tags associated with the Task
     * @return a String representing all tags
     */
    public String showTags() {
        StringBuilder result = new StringBuilder();
        if (!tags.isEmpty()) {
            result.append(" Tags: ");
            for (String tag : tags) {
                result.append(tag).append(" ");
            }
        }
        return result.toString();
    }
}
