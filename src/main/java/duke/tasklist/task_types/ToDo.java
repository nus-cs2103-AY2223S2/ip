package duke.tasklist.task_types;

import duke.duke_exception.DukeException;
import duke.utility.enums.UpdateType;

/**
 * Represents a <code>task</code> object with the todo tag into it.
 * 
 * @author Brian Quek
 */
public class ToDo extends Task {

    /**
     * Constructor for the ToDo object.
     */
    public ToDo(String name) {
        super(name);
    }

    public void Update(UpdateType type, String newValue) throws DukeException {
        if(type != UpdateType.name) {
            throw new DukeException("/by, /from, /to does not exist in ToDo Object.");
        }


        super.name = newValue;
    } 

    /**
     * @return a String containing the task type and todo details.
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

}
