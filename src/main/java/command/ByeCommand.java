package command;

import util.DukeException;

/**
 * Executes by command which terminates the chat.
 */
public class ByeCommand extends Command {

    /**
     * Displays goodbye message
     *
     * @throws DukeException
     */
    @Override
    public void executeCommand() throws DukeException {
        System.out.println("ByeBye! Come play with me again!");
    }
}
