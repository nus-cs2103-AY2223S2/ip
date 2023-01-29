package duke.commands;

import duke.tasks.TaskList;
import duke.ui.Ui;

public class ListCommand extends Command {
    private Ui ui;
    private TaskList taskList;

    public ListCommand(Ui ui, TaskList taskList) {
        this.ui = ui;
        this.taskList = taskList;
    }

    @Override
    public void action() {
        ui.listResponse(taskList);
    }
}
