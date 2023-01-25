/**
 * Represents a to-do task.
 */
public class ToDos extends Task {

    protected ToDos(boolean status, String[] content) {
        super(status, content[0]);
        /*
        if (content.isEmpty()) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        }

         */
    }

    protected String getTypeIcon() {
        return "T";
    }

    @Override
    protected String fileMessage() {
        return String.format("%s||%d||%s\n", getTypeIcon(), isDone ? 0 : 1, content);
    }
}
