package command;

import duke.Ui;

/**
 * Command to terminate duke.
 */
public class CommandBye extends Command {
    @Override
    public String execute() {
        return Ui.goodbyeMessage;
    }
}
