package duke.commands;

import duke.Storage;
import duke.Ui;

import duke.tasks.TaskList;

public class ListCommand implements Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showMessage(taskList.printList());
    }
}