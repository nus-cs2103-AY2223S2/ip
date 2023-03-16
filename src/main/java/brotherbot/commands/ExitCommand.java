package brotherbot.commands;

import brotherbot.storage.TaskList;
import brotherbot.ui.Ui;

public class ExitCommand extends Command {

    /**
     * Constructor to create an ExitCommand object.
     *
     * @param input Input string required for command execution.
     */
    public ExitCommand(String input) {
        super(input);
    }

    /**
     * Executes command.
     *
     * @param storage Existing TaskList object required for command execution.
     * @param ui Ui object required for command execution.
     */
    public void execute(TaskList storage, Ui ui) {
        super.isExit = true;
        ui.toUser("ok see you brother all love no cringe!");
    }
}
