public class Task {
    private final String description;
    private boolean isComplete;
    public Task(String description) {
        this.description = description;
        this.isComplete  = false;
    }
    public String getDescription() {
        return this.description;
    }
    public boolean isComplete() {
        return this.isComplete;
    }
    public void mark() {
        this.isComplete = true;
    }
    public void unmark() {
        this.isComplete = false;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[] " + this.description);
        if (this.isComplete) {
            sb.insert(1, "X");
        }
        return sb.toString();
    }
}
