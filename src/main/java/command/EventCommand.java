package command;

import duke.DukeException;

/**
 * Class for calling event command
 */
public class EventCommand extends TaskCommand {
    private static final int NUM_COMPONENTS = 6;

    /**
     * Default constructor
     * @param command the user-input command
     * @param doesPrint whether to print to ui
     * @throws DukeException if the command is incomplete
     */
    public EventCommand(String command, boolean doesPrint) throws DukeException {
        super(command, doesPrint, NUM_COMPONENTS);
    }
}
