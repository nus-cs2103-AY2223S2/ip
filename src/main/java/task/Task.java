package task;

/**
<<<<<<< HEAD
 * Task object encapsulating Todo, Deadline, and Event tasks.
=======
 * Task object to be stored inside the task list.
>>>>>>> master
 */
public abstract class Task {
    private boolean marked;
    private final String content;

    /**
<<<<<<< HEAD
     * Constructor for Task object.
     * @param content Content to be placed in the task.
=======
     * Constructor for Task.
     * @param content Content to be placed inside the task.
>>>>>>> master
     */
    public Task(String content) {
        this.marked = false;
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
        switch (taskType) {
        case 'T':
            return Todo.create(content);
        case 'D':
            return Deadline.create(content);
        case 'E':
            return Event.create(content);
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

    @Override
    public String toString() {
        String markedStatus = this.isMarked() ? "X" : " ";
        return String.format("[%s] %s", markedStatus, this.getContent());
    }

    /**
     * Formats the task object for storage.
     * @return The String representing the task.
     */
    public abstract String toStorageString();
}
