abstract class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        if (description.isEmpty()) {
            throw new IllegalArgumentException("Task cannot be empty!");
        }
        this.description = description;
        this.isDone = false;
    }
    public String getDescription() {
        return description;
    }
    public boolean isDone() {
        return isDone;
    }
    public void setDone(boolean done) {
        isDone = done;
    }
    public String printTask() {
        return String.format("[%s] %s", (isDone ? "X" : " "), description);
    }
}
