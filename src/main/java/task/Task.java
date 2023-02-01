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
    private boolean checked;

    /**
     * Public constructor.
     *
     * @param str Task name.
     * @param checked Boolean for whether current task is marked.
     */
    public Task(String str, boolean checked) {
        this.str = str;
        this.checked = checked;
    }

    public boolean isChecked() {
        return checked;
    }
    public String getCheckedString() {
        return checked ? "1" : "0";
    }

    public String getStr() {
        return str;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public abstract String getStoreTaskString();
    public abstract String getTaskType();
}
