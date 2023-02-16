package duke.command;

import duke.Ui;
import duke.Storage;
import duke.TaskList;

public class ListCommand extends Command {
    public ListCommand() {
    }

    public void execute(TaskList taskList, Storage storage, Ui ui) {
        ui.printList(taskList);
    }
}
