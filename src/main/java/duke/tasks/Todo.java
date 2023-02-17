package duke.tasks;

import duke.exceptions.DukeInvalidInputException;

/**
 * Represents a type of task called todo that only has a name.
 * @author lukkesreysandeur
 */
public class Todo extends Task {
    /**
     * Initialises the todo object.
     *
     * @param name The name of the task.
     */
    private Todo(String name) {
        super(name);
    }

    /**
     * Creates a new todo object, throws error if input is invalid.
     *
     * @param input A string that contains the parameters entered after the user command.
     * @return The created todo object.
     * @throws DukeInvalidInputException
     */
    public static Todo createTodo(String input) throws DukeInvalidInputException {
        String sanitisedInput  = input.trim();
        if (input.isEmpty() || sanitisedInput.isEmpty()) {
            throw new DukeInvalidInputException("Todos need a description");
        }
        return new Todo(sanitisedInput);
    }

    /**
     * Returns the string representation of the todo.
     *
     * @return A string representing the todo.
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    /**
     * Converts the todo into a text format that can be saved to a text file.
     * 
     * @return A string representing the todo in a different format.
     */
    @Override
    public String toText() {
        return String.format("T %d %s\n", super.isDone ? 1 : 0, super.name);
    }

    /**
     * Returns the name of the Todo.
     *
     * @return The name of the Todo.
     */
    @Override
    public String getName() {
        return super.getName();
    }

    /**
     * Returns true if the object is the same as the Todo, false otherwise.
     *
     * @param o Object to be compared to.
     * @return True if the object is a Todo and shares the same name;
     *         false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Todo) {
            Todo t = (Todo) o;
            return t.getName().equals(this.getName());
        }
        return false;
    }

    /**
     * Returns the hashcode of the Todo.
     *
     * @return The corresponding hashcode.
     */
    @Override
    public int hashCode() {
        int result = 19;
        if (this.getName() != null) {
            result = 31 * result + this.getName().hashCode();
        }
        return result;
    }
}
