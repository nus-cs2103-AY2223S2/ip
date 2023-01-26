public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    private String getTaskClass() {
        return "T";
    }

    public String toString() {
        return String.format("[%s][%s] %s", this.getTaskClass(), this.getStatusIcon(), this.description);
    }
}
