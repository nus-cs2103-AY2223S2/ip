package elise.tasks;

/**
 * Represents a to-do task.
 */
public class ToDo extends Task {

    /**
     * Constructor for ToDo
     *
     * @param status Completed or not.
     * @param content Content of message.
     */
    public ToDo(boolean status, String[] content) {
        super(status, content[0]);
    }

    /**
     * Returns the type icon dedicated to the todo task.
     *
     * @return Type icon of todo task.
     */
    protected String getTypeIcon() {
        return "T";
    }

    /**
     * Returns string representation of todo task to be stored in file.
     *
     * @return String representation of todo task to be stored in file.
     */
    @Override
    public String fileMessage() {
        return String.format("%s||%d||%s\n", getTypeIcon(), isDone ? 1 : 0, content);
    }
}
