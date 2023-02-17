package smudge.command;

import smudge.main.Storage;
import smudge.main.Tasklist;
import smudge.main.Ui;

public class HelpCommand extends Command {

    public String execute(Tasklist taskList, Ui ui, Storage storage) {
        return ui.printHelp();
    }
}