package task;

/**
 * Task object encapsulating Todo, Deadline, and Event tasks.
 */
public abstract class Task {
    private boolean marked;
    private final String content;
    private final boolean isHigh;

    /**
     * Constructor for Task object.
     * @param content Content to be placed in the task.
     */
    public Task(String content, boolean isHigh) {
        this.marked = false;
        this.content = content;
        this.isHigh = isHigh;
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
        switch (taskType) {
        case 'T':
            return Todo.create(content, isHigh);
        case 'D':
            return Deadline.create(content, isHigh);
        case 'E':
            return Event.create(content, isHigh);
        default:
            return null;
        }
    }

    /**
     * Either marks or unmarks the task.
     * @param isToMark Whether to mark or unmark the task.
     */
    public void mark(boolean isToMark) {
        this.marked = isToMark;
    }

    /**
     * Returns whether the task is marked.
     * @return Whether the task is marked.
     */
    public boolean isMarked() {
        return this.marked;
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

    @Override
    public String toString() {
        String markedStatus = this.isMarked() ? "X" : " ";
        String priorityStatus = this.isHigh ? "!!" : "";
        return String.format("[%s] %s%s", markedStatus, this.getContent(), priorityStatus);
    }

    /**
     * Formats the task object for storage.
     * @return The String representing the task.
     */
    public abstract String toStorageString();
}
