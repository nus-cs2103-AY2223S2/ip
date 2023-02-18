package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class SortDeadlineCommand extends Command {
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return taskList.getSortedDeadlines();
    }
}
