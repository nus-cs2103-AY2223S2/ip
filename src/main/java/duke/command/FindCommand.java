package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class FindCommand extends Command {
    private String searchLine;
    public FindCommand(String searchLine) {
        this.searchLine = searchLine;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printToFormat(tasks.find(searchLine));
    }
}
