package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        return ui.showFoundTasks(tasks.findKeywordTasks(keyword));
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String toString() {
        return "Command: Find tasks containing \"" + keyword + "\".";
    }
}
