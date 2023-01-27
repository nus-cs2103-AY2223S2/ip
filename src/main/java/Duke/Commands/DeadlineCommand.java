package Duke.Commands;

import Duke.entities.Deadline;
import Duke.entities.TaskList;
import Duke.Ui.Ui;

public class DeadlineCommand extends Command {

    @Override
    public void processCommand() {
        System.out.println("abstract method invoked");
    }

    public void processCommand(TaskList list, String desc, String by, Ui ui) {
        Deadline deadline = new Deadline(desc, by);
        list.addTask(deadline);
        ui.print(String.format("Received, I've added the following deadlines:\n %s", deadline.toString()));
        ui.print(String.format("Now you have %d tasks in the list.", list.getSize()));
        ui.printDivider();
    }
}
