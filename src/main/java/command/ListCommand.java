package command;

import main.Storage;
import main.TaskList;
import main.Ui;

public class ListCommand extends Command{

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        String res = "Here are the tasks in your list: " + "\n" + taskList + "\n" +
                "You have " + taskList.getTotalTasks() + " tasks";
        ui.printFormattedString(res);
    }

}
