package duke;

/**
 * Represents a to-do task.
 */
public class ToDo extends Task {

    protected ToDo(boolean status, String[] content) {
        super(status, content[0]);
    }

    protected String getTypeIcon() {
        return "T";
    }

    @Override
    protected String fileMessage() {
        return String.format("%s||%d||%s\n", getTypeIcon(), isDone ? 1 : 0, content);
    }
}
