package duke.commands;

import duke.Ui;

public class ExitCommand extends Command {

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public String handleCommand() {
        return Ui.showBye();
    }
}
