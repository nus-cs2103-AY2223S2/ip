package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ListCommand extends Command{
    @Override
    public void executeCommand(TaskList taskList, Storage storage, Ui ui) {
        ui.printText( "Here are the tasks in your list:");
        String printedList = taskList.printList();
        ui.printText(printedList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
