public class Task {

    private final String details;
    private boolean isCompleted = false;

    public Task(String details) {
        this.details = details;
    }

    public String getDetails() {
        return details;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void changeStatus(boolean hasCompleted) {
        if (hasCompleted) {
            isCompleted = true;
            return;
        }
        isCompleted = false;
    }
}
