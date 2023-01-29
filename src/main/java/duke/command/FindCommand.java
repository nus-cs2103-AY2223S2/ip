package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class FindCommand extends Command {
    private String keyword;
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String foundTasks = tasks.findTasks(this.keyword);
        ui.formResponse("Tasks matching your keyword:" + System.lineSeparator() + foundTasks);
    }

}
