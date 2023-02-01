package kira.command;

import kira.storage.TaskList;
import kira.ui.Ui;

/**
 * Command for BYE.
 */
public class ByeCommand extends Command {

    /**
     * Constructs an executable to shut down the bot.
     */
    public ByeCommand() {

    }

    @Override
    public boolean execute(Ui ui, TaskList tasklist) {
        ui.endMsg();
        return false;
    }

}
