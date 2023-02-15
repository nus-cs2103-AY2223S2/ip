package types.data;

/**
 * Task data type.
 */
public abstract class Task {
    protected String name;
    protected String typeMark;
    protected TaskStatus status;

    protected Task(String n, String tm) {
        name = n;
        typeMark = tm;
        status = TaskStatus.INCOMPLETE;
    }

    /**
     * Gets the symbol to indicate completion status of the task.
     * @return "X" if COMPLETED, "" otherwise.
     */
    public String getDoneMark() {
        switch (status) {
        case COMPLETED:
            return "[X]";
        case INCOMPLETE:
            return "[ ]";
        default:
            return "ERROR";
        }
    }

    /**
     * Gets the symbol to indicate subtype as specified of the task.
     * @return The string as specified in the constructor.
     */
    public String getTypeMark() {
        return String.format("[%s]", typeMark);
    }

    /**
     * Gets the name of the task.
     * @return Its name.
     */
    public String getName() {
        return name;
    }

    /**
     * Marks the task as COMPLETED.
     */
    public void setDone() {
        status = TaskStatus.COMPLETED;
    }

    /**
     * Marks the task as INCOMPLETE.
     */
    public void setUndone() {
        status = TaskStatus.INCOMPLETE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format("%s%s %s", getTypeMark(), getDoneMark(), name);
    }
}
