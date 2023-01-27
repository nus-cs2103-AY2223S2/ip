package command;

import duke.DukeException;

/**
 * Command for deadline task.
 */
public class DeadlineCommand extends TaskCommand {
    /**
     * Constructor
     * @param command user-input command
     * @param doesPrint whether to print the message
     */
    public DeadlineCommand(String command, boolean doesPrint) throws DukeException {
        super(command, doesPrint, 4);
    }
}
