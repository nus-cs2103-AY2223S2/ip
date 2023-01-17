public class Task {
    private boolean completed;
    private String description;

    Task (String description) {
        this.description = description;
        this.completed = false;
    }

    String getStatusIcon() {
        return this.completed ? "X" : " ";
    }

    String getDescription() {
        return this.description;
    }

    void setCompleted(boolean completed) {
        this.completed = completed;
    }

}
