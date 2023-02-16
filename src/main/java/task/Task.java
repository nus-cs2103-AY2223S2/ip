package task;

/**
 * Abstract class for task.
 */
public abstract class Task {
    /**
     * Task name.
     */
    private final String task;
    /**
     * Boolean for whether current task is marked.
     */
    private boolean isChecked;
    /**
     * Public constructor.
     *
     * @param task      Task name.
     * @param isChecked Boolean for whether current task is marked.
     */
    public Task(String task, boolean isChecked) {
        this.task = task;
        this.isChecked = isChecked;
    }

    public boolean isChecked() {
        return isChecked;
    }
    public String getCheckedString() {
        return isChecked ? "1" : "0";
    }

    public String getTask() {
        return task;
    }

    public void setChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }

    public abstract String getStoreTaskString();
    public abstract String getTaskType();
}
