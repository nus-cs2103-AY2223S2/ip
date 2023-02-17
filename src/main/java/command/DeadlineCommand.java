package command;

import duke.DukeException;

/**
 * Command for deadline task.
 */
public class DeadlineCommand extends TaskCommand {
    private static int NUM_COMPONENTS = 4;

    /**
     * Constructor
     * @param command user-input command
     * @param doesPrint whether to print the message
     * @throws DukeException when parsing error occurs
     */
    public DeadlineCommand(String command, boolean doesPrint) throws DukeException {
        super(command, doesPrint, NUM_COMPONENTS);
    }
}
