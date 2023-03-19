package smudge.command;

import smudge.main.Storage;
import smudge.main.Tasklist;
import smudge.main.Ui;

public class ExitCommand extends Command {

    public String execute(Tasklist taskList, Ui ui, Storage storage) {
        super.switchExitCondition();
        return ui.printBye();
    }
}
