package duke.tasks;

public class Todo extends Task {
    public Todo(String instruction) {
        super(instruction);
        this.description = instruction.substring(5);
    }

    @Override
    public String toString() {
        return String.format("[T]%s %s", super.toString(), description);
    }
}
