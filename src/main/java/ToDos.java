/**
 * Represents a to-do task.
 */
public class ToDos extends Task {

    protected ToDos(String content) throws DukeException {
        super(content);
        if (content.isEmpty()) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        }
    }

    protected String getTypeIcon() {
        return "T";
    }
}
