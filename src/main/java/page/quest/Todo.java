package page.quest;

/**
 * Represents a todo, i.e. a quest with no specific start or end date.
 */
public class Todo extends Quest {

    public Todo(String description) {
        super(description);
    }


    @Override
    public void edit(String[] args) {
        if (!args[0].equals("")) {
            setDescription(args[0]);
        }
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
