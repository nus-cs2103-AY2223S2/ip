package panav.task;

import panav.exception.ToDoDescriptionException;

/**
 * Class to represent an ToDo task. It contains attributes for it's description.
 */
public class ToDo extends Task {

    public static final String COMMAND_WORD = "todo";

    /**
     * Constructor to initialise attributes.
     * @param description tdo description.
     * @throws ToDoDescriptionException if there is no description.
     */
    public ToDo(String description) throws ToDoDescriptionException {
        super(description);
        if (description.length() == 0) {
            throw new ToDoDescriptionException("OOPS!!! The description of a todo cannot be empty.");
        }
    }

    /**
     * Returns command word of task.
     * @return command word.
     */
    @Override
    public String getCommand() {
        return ToDo.COMMAND_WORD;
    }

    /**
     * Method to get that just returns the String representation of the tasks
     * containing just that part on which the 'find' command can search.
     * For eg. a find command should not be able to search "find from" and then
     * all the events are displayed. It should only be able to search the description and
     * timeframes.
     * @return searchable part of the string
     */
    @Override
    public String findablePart() {
        return this.description;
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
