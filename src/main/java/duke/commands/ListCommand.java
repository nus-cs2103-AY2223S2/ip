package duke.commands;
import duke.Ui;
import duke.Storage;
import duke.tasks.TaskList;

public class ListCommand implements Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showMessage(taskList.printList());
    }
}