package duke.tasks;

/**
 * Represents a todo task.
 * 
 * @author Samarth Verma
 */
public class Todo extends Task {
    private String desc;

    /**
     * Creates a new Todo.
     * @param id The id of the todo.
     * @param description The description of the todo.
     */
    public Todo(int id, String description) {
        super(id);
        desc = description;
    }

    /**
     * Returns the description of the todo.
     * @return the description of the todo.
     */
    public String description() {
        return desc;
    }

    @Override
    public String toString() {
        String isDone = isCompleted() ? "X" : " ";
        return String.format("[T][%s] %s", isDone, description());
    }

    @Override
    public String serialize() {
        String isDone = isCompleted() ? "1" : "0";
        return String.format("T|%s|%s|%s", id(), isDone, description());
    }

    /**
     * Deserializes a todo from a string.
     * @param s The string to deserialize from.
     * @return The deserialized todo.
     */
    public static Todo deserialize(String s) {
        String[] parts = s.split("\\|");
        Todo todo = new Todo(Integer.parseInt(parts[1]), parts[3]);
        if (parts[2].equals("1")) {
            todo.markCompleted();
        }
        return todo;
    }
}
