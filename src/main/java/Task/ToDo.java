package Task;

/**
 * Object class of ToDo
 */
public class ToDo extends Task {
    protected static final int TODO = 4;
    protected String type = "[T]";

    public ToDo(String description) {
        super(description);
    }

    /**
     * Create todo task
     * @param input
     * @return todo object
     */
    public static ToDo createToDo(String input) {
        String description = input.substring(TODO + 1);
        return new ToDo((description));
    }

    @Override
    public String toString() {
        return type + super.toString();
    }

}
