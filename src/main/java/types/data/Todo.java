package types.data;

/**
 * Represents the To-do data type.
 */
public class Todo extends Task {
    private Todo(String s) {
        super(s, "T");
    }

    /**
     * Creates a new To-do object.
     * @param n Description string.
     * @return The newly constructed to-do object.
     */
    public static Todo create(String n) {
        return new Todo(n);
    }
}
