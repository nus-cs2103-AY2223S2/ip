public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    public String getTaskIcon() {
        return "[T]";
    }

    public String currToPrint() {
        return this.getTaskIcon() + this.getStatusIcon() + " " + this.getDescription();
    }
}
