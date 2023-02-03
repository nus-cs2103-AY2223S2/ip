package lele.command;

import lele.storage.Storage;
import lele.task.TaskList;
import lele.ui.Ui;

public class FindCommand extends Command {
    String regex;

    public FindCommand(String regex) {
        this.regex = regex;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.printFind(taskList, this.regex);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
