public class Task {
    private boolean status = false;
    private String taskName;

    public Task (String name) {
        this.taskName = name;
    }

    public boolean getStatus() {
        return this.status;
    }

    public String getTaskName() {
        return this.taskName;
    }

    public void setStatus(boolean state) {
        this.status = state;
    }
}
