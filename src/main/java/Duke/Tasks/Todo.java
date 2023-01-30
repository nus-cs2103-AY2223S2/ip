package Duke.Tasks;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String printTask() {
        return String.format("T | %d | %s ", isComplete ? 1 : 0, description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
