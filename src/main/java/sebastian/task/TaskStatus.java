package sebastian.task;

/**
 * An Enum class representing the completion status of a task
 */
public enum TaskStatus {
    DONE("X"),
    NOT_DONE(" ");

    private final String status;

    /**
     * Constructor
     * @param status completion status of the task
     */
    TaskStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "[" + this.status + "]";
    }
}

