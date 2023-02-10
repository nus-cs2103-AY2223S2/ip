package duke.task;

import duke.storage.Storage;

/**
 * Subclass of <code>Task</code> class used by <code>Duke</code>.
 *
 * @author Bo Kuan (LG17)
 * @version CS2103T AY22/23 Semester 2
 */
public class ToDo extends Task {
    /**
     * Constructor for a <code>Todo</code>.
     *
     * @param description String describing the <code>Todo</code>.
     */
    public ToDo(String description) {
        super(description);
    }
    /**
     * Returns the string representation of a <code>Todo</code>.
     *
     * @return The string representation of a <code>Todo</code>.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
    /**
     * Returns the string representation of a <code>Todo</code> for storage.
     *
     * @return The string representation of a <code>Event</code> for storage.
     */
    @Override
    public String getFileFormatString() {
        //to be split using "@"
        String s = Storage.SPLITTER;
        return Task.TODO_FILE_FORMAT + s + this.isDone + s + this.description;
    }
}
