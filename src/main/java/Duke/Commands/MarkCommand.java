package Duke.Commands;

import Duke.entities.Task;
import Duke.entities.TaskList;
import Duke.Ui.Ui;

public class MarkCommand extends Command {
    @Override
    public void processCommand() {
        System.out.println("abstract method invoked");
    }

    public void processCommand(TaskList list, int index, Ui ui) {
        Task task = list.getTask(index);
        task.setDone();
        ui.print(String.format("Nice, this task has been marked as done:\n %s", task.toString()));
        ui.printDivider();
    }
}
