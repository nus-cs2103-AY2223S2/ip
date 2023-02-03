package duke.commands;

import duke.Ui;
import duke.storage.Storage;
import duke.tasklist.TaskList;

/**
 * Command to search for keywords in task of a task list.
 */
public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        super("FIND");
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.find(this.keyword, ui);
    }
}

