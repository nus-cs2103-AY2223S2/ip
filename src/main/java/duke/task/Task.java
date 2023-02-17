package duke.task;

import duke.exception.DukeException;

/**
 * The Task class represents a todo item with a description and can be marked / unmarked as complete.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    protected Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    protected Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    protected void mark() {
        isDone = true;
    }

    protected void unmark() {
        isDone = false;
    }

    public String getStatusIndicator() {
        return "[" + (isDone ? "X" : " ") + "]";
    }

    @Override
    public String toString() {
        return "Todo " + getTaskDescription();
    }

    protected String getTaskDescription() {
        return getStatusIndicator() + " " + description;
    }

    /**
     * Provides a serialized format for the Task object.
     *
     * @return serialized format for the Task object
     */
    public String serialize() {
        // TODO: Handle case where description contains "|"
        return String.join("|", "T", isDone ? "1" : "0", description);
    }

    /**
     * Deserializes a serialized Task object.
     *
     * @param data string of serialized Task object to deserialize
     * @return deserialized Task object
     * @throws DukeException
     */
    public static Task deserialize(String data) throws DukeException {
        String[] split = splitDataStr(data);
        // TODO: Verify task data (similar to processing queries)
        return new Task(split[2], split[1].equals("1"));
    }

    protected static String[] splitDataStr(String dataStr) {
        return dataStr.split("[|]");
    }
}
