package command;

import java.util.List;
import storage.Storage;
import taskList.TaskList;
import tasks.Task;
import ui.Ui;

public class ListCommand extends Command {

    public ListCommand() {
        super(Commands.LIST);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println("Here are the tasks in your list:");
        List<Task> immutableTaskList = tasks.getList();

        ui.showTaskList(immutableTaskList);
    }
}
