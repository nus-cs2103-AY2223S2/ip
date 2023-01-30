package duke.data.task;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String storageStr() {
        return "T | " + super.getStatusValue() + " | " + super.description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
