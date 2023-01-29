package crystal.command;

import crystal.Storage;
import crystal.TaskList;
import crystal.Ui;

public class FindCommand extends Command {

    public String word;
    public FindCommand(String word) {
        this.word = word;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printFind(tasks, this.word);
    }

    public boolean isExit() {
        return false;
    }
}
