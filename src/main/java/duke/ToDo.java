package duke;

public class ToDo extends Task {

    public ToDo(String name) {
        super(name);
    }

    @Override
    public String toSave() {
        if (super.isDone()) {
            return "T | 1 | " + super.getName() + "\n";
        } else {
            return "T | 0 | " + super.getName() + "\n";
        }
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
