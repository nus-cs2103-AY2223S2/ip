package duke.command;

import duke.DukeException;

/**
 * A command that asks Dukes to exit
 * @author Junyi
 */
public class ByeCommand extends Command {

    private ByeCommand() {}

    /**
     * Factory method to create bye command
     * @return An instance of ByeCommand.
     */
    public static ByeCommand createByeCommand() {
        return new ByeCommand();
    }

    @Override
    public String execute() throws DukeException {
        return "Till next time...";
    }

    /**
     * Returns true here as this command should close Duke after
     * @return True to terminate Duke
     */
    @Override
    public boolean shouldTerminate() {
        return true;
    }
}
