package duke.tasks;

/**
 * Represents a todo task.
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String printTask() {
        return String.format("[T]%s", super.printTask());
    }

    @Override
    public String formatForFile() {
        return String.format("%s|%s", "T", super.formatForFile());
    }
    @Override
    public boolean isEqual(Task anotherTodo) {
        return this.description.equals(anotherTodo.description);
    }
}
