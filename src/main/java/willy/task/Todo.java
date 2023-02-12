package willy.task;

/**
 * Represents a Todo
 */
public class Todo extends Task {
    private String code;

    /**
     * Creates a todo with a string msg
     * @param msg
     */
    public Todo(String msg) {
        super(msg);
        this.code = "[T]";
    }
    /**
     * Get code
     * @return String
     */
    public String getCode() {
        return this.code;
    }

    /**
     * Converts into a string representation
     */
    @Override
    public String toString() {
        return code + super.toString();
    }

}
