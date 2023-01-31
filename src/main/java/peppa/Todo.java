package peppa;

/**
 * Represents a todo task. Todos are given by the user in the format todo {desc}.
 */
public class Todo extends Task {

    /**
     * Constructs a todo task with the specified name.
     *
     * @param name Name/description of the todo.
     */
    public Todo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toFormatString() {
        return "T | " + (super.done ? "1" : "0") + " | " + super.name;
    }
}
