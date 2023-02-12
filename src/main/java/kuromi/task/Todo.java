package kuromi.task;

/**
 * Todo task represented by description. Extends from Task class.
 */
public class Todo extends Task {
    /**
     * kuromi.gui.component.MainWindow.kuromi.exceptions.KuromiException.Main constructor (for invocation by most classes)
     *
     * @param description Description of a Todo.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Secondary constructor (for invocation by Storage to put task in data into TaskList)
     *
     * @param description Description of a Todo.
     * @param isDone Status of a Todo.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Get the symbol of a Todo.
     *
     * @return Symbol of a Todo.
     */
    @Override
    public String getSymbol() {
        return "T";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
