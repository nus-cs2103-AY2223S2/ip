package duke;

public class Task {
    protected String description;
    protected TaskStatus status;

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


    public Task(String description) {
        this.description = description;
        this.status = TaskStatus.NOT_DONE;
    }

    public Task(String description, String status) {
        this.description = description;
        this.status = TaskStatus.valueOf(status);
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    public String toData() {
        return this.status.toString() + "|" + this.description;
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

    public void markDone() {
        this.status = TaskStatus.DONE;
    }

    public void markUndone() {
        this.status = TaskStatus.NOT_DONE;
    }

    public void markDoing() {
        this.status = TaskStatus.DOING;
    }

    public String getDescription() {
        return this.description;
    }
}
