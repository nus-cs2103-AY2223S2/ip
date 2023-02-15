package duke;

/**
 * Superclass for all Tasks.
 * Contains basic description of tasks and its status.
 */
public class Task {
    protected String description;
    protected TaskStatus status;
    protected String tag = "NA";

    /**
     * Enumeration for TaskStatus.
     * DONE: Task is done.
     * NOT_DONE: Task is not done.
     * DOING: Task is currently in progress.
     */
    public enum TaskStatus {
        DONE {
            public String toString() {
                return "DONE";
            }
        },
        NOT_DONE {
            public String toString() {
                return "NOT_DONE";
            }
        },
        DOING {
            public String toString() {
                return "DOING";
            }
        }
    }

    /**
     * Constructor for Task.
     * @param description String description of Task.
     */
    public Task(String description) {
        String[] nameAndTag = description.split("#", 2);
        this.description = nameAndTag[0];
        if (nameAndTag.length == 1) {
            this.tag = "NA";
        } else {
            this.tag = nameAndTag[1];
        }
        this.status = TaskStatus.NOT_DONE;
    }

    /**
     * Constructor for Task to be mainly used by Storage class to load tasks from data.txt file.
     * @param description String description of Task.
     * @param status Status of the task.
     */
    public Task(String description, String status) {
        String[] nameAndTag = description.split("#");
        this.description = nameAndTag[0];
        this.tag = nameAndTag[1];
        this.status = TaskStatus.valueOf(status);
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description + " #" + this.tag;
    }

    public String toData() {
        return this.status.toString() + "|" + this.description + "#" + this.tag;
    }

    public String getStatusIcon() {
        switch(status) {
        case DONE:
            return "X";
        case NOT_DONE:
            return " ";
        case DOING:
            return "~";
        default:
            return "?";
        }
    }

    /**
     * Marks itself done.
     */
    public void markDone() {
        this.status = TaskStatus.DONE;
    }

    /**
     * Marks itself not done.
     */
    public void markUndone() {
        this.status = TaskStatus.NOT_DONE;
    }

    /**
     * Marks itself as in progress.
     */
    public void markDoing() {
        this.status = TaskStatus.DOING;
    }

    /**
     * Returns description of itself.
     * @return String description.
     */
    public String getDescription() {
        return this.description;
    }

    public String getTag() {
        return this.tag;
    }

    public void tagTask(String tag) {
        this.tag = tag;
    }
}
