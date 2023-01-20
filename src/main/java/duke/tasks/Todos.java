package duke.tasks;

public class Todos extends Task {
    public Todos(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toWrite() {
        return "T | " + super.toWrite() + "\n";
    }
}
