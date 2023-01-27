package Commands;

import entities.Task;
import entities.TaskList;
import Ui.Ui;

public class ListCommand extends Command {

    @Override
    public void processCommand() {
        System.out.println("abstract method invoked");
    }

    public void processCommand(TaskList list, Ui ui) {
        for (int i = 0; i < list.getSize(); i++) {
            Task t = list.getTask(i);
            ui.print(String.format("%d.%s", i + 1, t.toString()));
        }
        ui.printDivider();
    }
}
