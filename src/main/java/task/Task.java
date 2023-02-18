package task;

/**
 * This class represents a task.
 * A task has a name and a mark indicating whether it's done or not.
 */
public class Task {
    static final String DIVIDER = " | ";
    private boolean isMarked;
    private String name;
    private boolean isTagged;
    private String tag;

    /**
     * Constructs a new task with the given name.
     * @param name the name of the task
     */
    public Task(String name) {
        this.isMarked = false;
        this.name = name;
        this.isTagged = false;
    }

    /**
     * Marks the task as done.
     */
    public void markDone() {
        isMarked = true;
    }

    /**
     * Unmarks the task.
     */
    public void unmark() {
        isMarked = false;
    }

    /**
     * Returns the status icon of the task, either "X" or " ".
     * @return the status icon of the task
     */
    public String getStatusICon() {
        return (isMarked ? "[X] " : "[ ] ");
    }

    public String getTagIcon() {
        return isTagged ? " #" + this.tag : "";
    }

    /**
     * Returns whether the current Task's name contains the specified keyword.
     * @param keyword the keyword to search for in the task's name
     * @return true if the task's name contains the specified keyword, false otherwise
     */
    public boolean contains(String keyword) {
        return this.name.contains(keyword);
    }

    /**
     * Returns the task in a format suitable for saving.
     * @return the task in a format suitable for saving
     */
    public String toSaveFormat() {
        String markToInt = this.isMarked ? "1" : "0";
        String tagToInt = this.isTagged ? "1" : "0";
        return DIVIDER + markToInt + DIVIDER + tagToInt + DIVIDER + name;
    }
    public String getSaveTag() {
        return this.isTagged ? (DIVIDER + tag) : "";
    }

    /**
     * Returns a string representation of the task.
     * @return a string representation of the task
     */
    @Override
    public String toString() {
        return this.getStatusICon() + this.name + this.getTagIcon();
    }

    /**
     * Adds a tag to the task by setting the isTagged flag to true and setting the tag name.
     * @param tagName The name of the tag to be added to the task.
     * */
    public void addTag(String tagName) {
        isTagged = true;
        this.tag = tagName;
    }
}
