package seedu.duke;

import java.util.HashMap;

/**
 * Represents a basic to-do task
 */
public class ToDo extends Task {

    /**
     * Constructor for the ToDo task. Creates a task with an abbreviation of 'T'
     * @param parsed
     * @throws DukeException
     */
    public ToDo(HashMap<String, String> parsed) throws DukeException {
        super(parsed.get("todo"));
        abbreviation = 'T';
    }
}
