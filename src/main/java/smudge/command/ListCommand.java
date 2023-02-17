package smudge.command;

import smudge.main.Storage;
import smudge.main.Tasklist;
import smudge.main.Ui;

public class ListCommand extends Command {

    public String execute(Tasklist tasklist, Ui ui, Storage storage) {
        return ui.printTasks(tasklist);
    }
}