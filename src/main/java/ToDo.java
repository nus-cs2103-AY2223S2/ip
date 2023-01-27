public class ToDo extends Task {
    public ToDo(String taskName) {
        super(taskName);
        this.type = "T";
    }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }

    @Override
    public String encode() {
        return String.format("%s | %s | %s", this.type, this.isDone, this.taskName);
    }
}
