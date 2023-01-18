public class Task {
    private boolean isCompleted = false; //by default the task should not be completed
    private String task;

    public Task(String task) {
        this.task = task;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void markUncompleted() {
        isCompleted = false;
    }
    public void markCompleted() {
        isCompleted = true;
    }

    @Override
    public String toString() {
        return (isCompleted ? "[X] " + task : "[ ] " + task);
    }
}
