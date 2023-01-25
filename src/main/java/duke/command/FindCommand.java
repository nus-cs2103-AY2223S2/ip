package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class FindCommand extends Command {

    private String keyword;

    public FindCommand(String toFind) {
        this.keyword = toFind;
    }

    @Override
    public boolean isGoodbye() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        TaskList matchTasks = tasks.getMatchingTasks(this.keyword);
        if (matchTasks.getNumTasks() == 0) {
            ui.noMatchingTask();
        } else {
            ui.printTasks(matchTasks);
        }
    }
}
