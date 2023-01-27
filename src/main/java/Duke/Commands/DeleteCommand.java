package Duke.Commands;

import Duke.entities.Task;
import Duke.entities.TaskList;
import Duke.Ui.Ui;

public class DeleteCommand extends Command {
    @Override
    public void processCommand() {
        System.out.println("abstract method invoked");
    }


    public void processCommand(TaskList list, int index, Ui ui) {
        Task t = list.getTask(index);
        list.deleteTask(index);
        ui.print(String.format("ok, this task has been removed:\n %s", t.toString()));
        ui.print(String.format("Now you have %d tasks in the list", list.getSize()));
        ui.printDivider();
    }
}
