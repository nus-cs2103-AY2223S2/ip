package duke.command;

import duke.DukeException;

/**
 * A command that asks Dukes to exit
 * @author Junyi
 */
public class ByeCommand extends Command {

    @Override
    public boolean execute() throws DukeException {
        System.out.println("Till next time...");
        return true;
    }
}
