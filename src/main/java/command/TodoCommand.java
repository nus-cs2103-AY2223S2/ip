package command;

import duke.DukeException;

/**
 * Command for to-do tasks.
 */
public class TodoCommand extends TaskCommand {
    private static final int NUM_COMPONENTS = 2;

    /**
     * Constructor
     *
     * @param command user-input command
     * @param doesPrint whether to print messages
     * @throws DukeException when the input command cannot be parsed
     */
    public TodoCommand(String command, boolean doesPrint) throws DukeException {
        super(command, doesPrint, NUM_COMPONENTS);
    }
}
