public class Task {
    private String description;

    public Task (String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return "Added: " + this.description;
    }
}
