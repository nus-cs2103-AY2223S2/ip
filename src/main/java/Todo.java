public class Todo extends Task{
    protected String taskType;

    public Todo(String description) {
        super(description);
        this.taskType = "T";
    }

    public String toString() {
        return "[" + this.taskType + "][" + super.status + "] " + super.description;
    }
}
