package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

import java.util.ArrayList;

public class ListCommand extends Command {
    public ListCommand(String COMMAND_STRING) {
        super(Commands.LIST, COMMAND_STRING);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String msgHeader = "Current data in the list are:";
        ui.showMsg(msgHeader);

        ArrayList<Task> t = tasks.getTasks();
        for (int i = 0; i < t.size(); i++) {
            String output = String.format("%d. %s", i + 1, t.get(i));
            ui.showMsg(output);
        }
    }
}
