package duke.tasklist.task_types;

import duke.duke_exception.DukeException;
import duke.utility.enums.UpdateType;

/**
 * Represents a <code>task</code> object that contains a string holding the
 * <code>event</code> time
 * from a specific length stated by the user.
 * 
 * @author Brian Quek
 */
public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Constructor for the Event object.
     */
    public Event(String name, String from, String to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    /**
     * Updates a task based on type of update and newValue.
     * 
     * @param type the type of update.
     * @param newValue the updated value.
     * @return a reply message string.
     * @throws DukeException if type of update is not compatible with Event obj.
     */
    public void update(UpdateType type, String newValue) throws DukeException {
        if(type == UpdateType.by) {
            throw new DukeException("/by does not exist in Event Object.");
        }

        if(type == UpdateType.from) {
            this.from = newValue;
        }

        if(type == UpdateType.to) {
            this.to = newValue;
        }

        if(type == UpdateType.name) {
            super.name = newValue;
        }
    } 

    /**
     * @return a String containing the task type and event details.
     */
    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), from, to);
    }

}
