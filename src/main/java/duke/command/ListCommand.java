package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

import java.util.ArrayList;

public class ListCommand extends Command {
    public ListCommand(String commandString) {
        super(AvailableCommands.LIST, commandString);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        String msgHeader = "Current data in the list are:";
        ui.showMsg(msgHeader);

        ArrayList<Task> tasks = taskList.getTasks();
        for (int i = 0; i < tasks.size(); i++) {
            String output = String.format("%d. %s", i + 1, tasks.get(i));
            ui.showMsg(output);
        }
    }
}
