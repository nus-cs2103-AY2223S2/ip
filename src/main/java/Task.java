public class Task {
    String description;

    public Task(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}