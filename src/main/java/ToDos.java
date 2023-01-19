public class ToDos extends Task {
    public ToDos(String description) {
        super(description);
    }

    private String getTaskClass() {
        return "T";
    }

    public String toString() {
        return String.format("[%s][%s] %s", this.getTaskClass(), this.getStatusIcon(), this.description);
    }
}
