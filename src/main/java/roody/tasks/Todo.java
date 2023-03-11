package roody.tasks;

/**
 * Represents a Todo Task.
 */
public class Todo extends Task {
    private char type = 'T';

    /**
     * Creates a Todo with specified description.
     * @param description The specified description of the todo.
     */
    public Todo(String description) {
        super(description);
    }
    @Override
    public String saveTask() {
        return super.saveTask() + '|' + this.type;
    }
    @Override
    public char getType() {
        return type;
    }
    @Override
    public String toString() {
        char done = ' ';
        if (isDone()) {
            done = 'X';
        }
        return "[T][" + done + "] " + super.toString() + "";
    }
}
