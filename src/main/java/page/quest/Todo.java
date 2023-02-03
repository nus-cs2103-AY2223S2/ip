package page.quest;

/**
 * Represents a todo, i.e. a quest with no specific start or end date.
 */
public class Todo extends Quest {

    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the String representation of the todo.
     *
     * @return String representation of the todo.
     */
    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}
