package kira.task;

/**
 * ToDo is a type of task with only a description.
 */
public class ToDo extends Task {

    /**
     * Constructs a todo task with a description.
     *
     * @param description
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String saveFormat() {
        return "TODO\",\"" + super.saveFormat();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
