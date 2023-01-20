package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        super(false);
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList taskStore, Ui ui, Storage storage) {
        ui.showFind(taskStore.findTasksByKeyword(this.keyword));
    }

}
