package command;

import duke.DukeException;
import duke.Ui;

/**
 * Command for invalid inputs.
 */
public class CommandInvalid extends Command {

    @Override
    public String execute() throws DukeException {
        return Ui.getNoKeywordMessage();
    }
}
