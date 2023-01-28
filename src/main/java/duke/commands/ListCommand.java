package duke.commands;

import duke.tasks.TaskList;
import duke.ui.Ui;

public class ListCommand extends Command {
    private Ui ui;
    private TaskList tasks;

    public ListCommand(Ui ui, TaskList tasks) {
        this.ui = ui;
        this.tasks = tasks;
    }

    @Override
    public void action() {
        ui.listResponse(tasks);
    }
}
