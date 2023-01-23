package sebastian.command;

import sebastian.main.Storage;
import sebastian.main.TaskList;
import sebastian.main.Ui;

public class ListCommand extends Command{

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        String res = "Here are the tasks in your list: " + "\n" + taskList + "\n" +
                "You have " + taskList.getTotalTasks() + " tasks";
        ui.printFormattedString(res);
    }

}
