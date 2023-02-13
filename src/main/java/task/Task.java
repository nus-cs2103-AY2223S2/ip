package task;

/**
 * Abstract class for task.
 */
public abstract class Task {
    /**
     * Task name.
     */
    private final String str;
    /**
     * Boolean for whether current task is marked.
     */
    private boolean isChecked;

    /**
     * Public constructor.
     *
     * @param str Task name.
     * @param isChecked Boolean for whether current task is marked.
     */
    public Task(String str, boolean isChecked) {
        this.str = str;
        this.isChecked = isChecked;
    }

    public boolean isChecked() {
        return isChecked;
    }
    public String getCheckedString() {
        return isChecked ? "1" : "0";
    }

    public String getStr() {
        return str;
    }

    public void setChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }

    public abstract String getStoreTaskString();
    public abstract String getTaskType();
}
