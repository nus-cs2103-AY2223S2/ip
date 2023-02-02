package duke.command;

import duke.Ui;

public class ExitCommand extends Command {

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public void handleCommand(Ui ui) {
        ui.showBye();
    }
}
