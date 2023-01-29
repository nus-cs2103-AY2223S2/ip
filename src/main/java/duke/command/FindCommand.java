package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class FindCommand extends Command {
    private String query;

    public FindCommand(String query) {
        this.query = query;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList foundTasks = tasks.find(this.query);
        ui.printFoundTasks(foundTasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
