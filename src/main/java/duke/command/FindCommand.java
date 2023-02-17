package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class FindCommand extends Command {
    private final String query;

    public FindCommand(String query) {
        this.query = query;
    }
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList foundTasks = tasks.find(this.query);
        return ui.printFoundTasks(foundTasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
