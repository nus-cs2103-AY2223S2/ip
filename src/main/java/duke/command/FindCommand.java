package duke.command;

import java.util.ArrayList;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class FindCommand extends Command {
    private String query;

    public FindCommand(String query) {
        this.query = query;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ArrayList<Task> result = tasks.search(query);
        ui.showList(result);
    }

    public boolean isExit() {
        return false;
    }
}
