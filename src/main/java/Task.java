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

    @Override
    public String toString() {
        if (this.status) {
            return String.format("[X] %s\n", this.taskName);
        } else {
            return String.format("[ ] %s\n",this.taskName);
        }
    }
}
