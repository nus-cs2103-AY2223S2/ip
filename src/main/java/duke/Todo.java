package duke;

public class Todo extends Task {

    public Todo(String instruction) {
        super(instruction);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
