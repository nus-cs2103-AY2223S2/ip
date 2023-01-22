package duke.command;

import duke.DukeException;

public class ByeCommand extends Command {

    @Override
    public boolean execute() throws DukeException {
        System.out.println("Till next time...");
        return true;
    }
}
