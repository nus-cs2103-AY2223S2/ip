public class Task {
    private String name;
    private boolean completed = false;
    public Task(String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }
    public void mark() {
        this.completed = true;
    }
    public void unmark() {
        this.completed = false;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[] " + this.name);
        if (this.completed) {
            sb.insert(1, "X");
        }
        return sb.toString();
    }
}
