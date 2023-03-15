package jeo.command;

import jeo.database.TaskList;
import jeo.ui.Ui;

public class ListCommand extends Command {
    @Override
    public String execute(Ui ui, TaskList taskList) {
        return ui.showAllTasks(taskList);
    }

    @Override
    public String toString() {
        return "list";
    }
}
