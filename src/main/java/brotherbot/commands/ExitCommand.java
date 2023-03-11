package brotherbot.commands;

import brotherbot.storage.*;
import brotherbot.ui.Ui;

public class ExitCommand extends Command {
    public ExitCommand(String input) {
        super(input);
    }

    public void execute(TaskList storage, Ui ui) {
        super.isExit = true;
        ui.toUser("ok see you brother all love no cringe!");
    }
}
