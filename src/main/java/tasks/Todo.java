package tasks;


public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + "[" + this.getStatusIcon() + "] " + this.getDescription() + " ";
    }

    @Override
    public String writeToFile() {
        return "[T]" + "[" + this.getStatusIcon() + "] " + this.getDescription() + " ";
    }
}
