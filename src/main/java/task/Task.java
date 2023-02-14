package task;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Task object encapsulating Todo, Deadline, and Event tasks.
 */
public abstract class Task {
    private boolean isMarked;
    private final String content;
    private boolean isHigh;
    private final ArrayList<String> tags;

    /**
     * Constructor for Task object.
     * @param content Content to be placed in the task.
     */
    public Task(String content) {
        this.isMarked = false;
        this.isHigh = false;
        this.tags = new ArrayList<>();
        this.content = content;
    }

    /**
     * Creates a task object based on a taskType.
     * If taskType is invalid, then return null.
     * @param taskType Whether it is a Task.Todo, Task.Deadline or Task.Event.
     * @param content What to put in the task.
     * @return Task object.
     */
    public static Task create(char taskType, String content) {
        boolean isHigh = content.contains("high");
        content = content.replace(" high", "");
        Task task;
        switch (taskType) {
        case 'T':
            task = Todo.create(content);
            break;
        case 'D':
            task = Deadline.create(content);
            break;
        case 'E':
            task = Event.create(content);
            break;
        default:
            return null;
        }
        task.setPriorityLevel(isHigh);
        return task;
    }

    /**
     * Marks or unmarks the task.
     * @param isToMark Whether to mark or unmark the task.
     */
    public void mark(boolean isToMark) {
        this.isMarked = isToMark;
    }

    /**
     * Returns whether the task is marked.
     * @return Whether the task is marked.
     */
    public boolean isMarked() {
        return this.isMarked;
    }

    /**
     * Returns the contents of the task.
     * @return The contents of the task.
     */
    public String getContent() {
        return this.content;
    }

    /**
     * Checks when the task will be important.
     * @return Whether the deadline or start of event is soon.
     */
    public boolean isSoon() {
        return false;
    }

    /**
     * Sets the priority level.
     * @param isHigh Whether to set to high priority.
     */
    private void setPriorityLevel(boolean isHigh) {
        this.isHigh = isHigh;
    }

    /**
     * Sets the tag for the task.
     * @param tag Name of the tag.
     */
    private void addTag(String tag) {
        this.tags.add(tag);
    }

    @Override
    public String toString() {
        String markedStatus = this.isMarked() ? "X" : " ";
        String priorityStatus = this.isHigh ? "!!" : "";
        String tagsAsString = this.tags.stream().map(tag -> "#" + tag).collect(Collectors.joining(" "));
        return String.format("[%s] %s%s %s", markedStatus, this.getContent(), priorityStatus, tagsAsString);
    }

    /**
     * Formats the task object for storage.
     * @return The String representing the task.
     */
    public abstract String toStorageString();
}
