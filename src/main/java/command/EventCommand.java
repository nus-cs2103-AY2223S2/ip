package command;

import duke.DukeException;

/**
 * Class for calling event command
 */
public class EventCommand extends TaskCommand {
    public EventCommand(String command, boolean doesPrint) throws DukeException {
        super(command, doesPrint, 6);
    }
}
