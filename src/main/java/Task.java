public class Task {
    private String name;
    private boolean completed;

    public Task(String name) {
        this.name = name;
        this.completed = false;
    }

    public void makeCompleted() {
        this.completed = true;
    }

    public boolean isCompleted() {
        return this.completed;
    }
}
