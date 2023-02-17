package duke;

public class ToDo extends Task {

    /**
     * Constructor for todo task
     * @param name Description of todo task
     */
    public ToDo(String name) {
        super(name);
    }

    /**
     * Formats todo task to saving format
     * @return Formatted task string
     */
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
