package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
public class FindCommand extends Command {

    private final String keyword;
    FindCommand(String keyword) {
        this.keyword = keyword;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showBunny();
        ui.find(keyword, tasks);
    }

}