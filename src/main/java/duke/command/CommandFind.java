package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a Find command.
 */
public class CommandFind extends Command {
    private String[] keyword;
    public CommandFind(String... keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String foundTasks = tasks.findTasks(this.keyword);
        return ui.formResponse("LeTasks matching your keywords:\n" + foundTasks);
    }
}
