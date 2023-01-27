package Commands;

import entities.Task;
import entities.TaskList;
import Ui.Ui;

public class UnmarkCommand extends Command {
    @Override
    public void processCommand() {
        System.out.println("abstract method invoked");
    }

    public void processCommand(TaskList list, int index, Ui ui) {
        Task task = list.getTask(index);
        task.setUndone();
        ui.print(String.format("Nice, this task has been marked as done:\n %s", task.toString()));
        ui.printDivider();
    }
}
