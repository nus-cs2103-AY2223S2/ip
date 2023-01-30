package duke.command;

import duke.DukeException;

/**
 * A command that asks Dukes to exit
 * @author Junyi
 */
public class ByeCommand extends Command {

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
