package Duke.Commands;

import Duke.entities.Deadline;
import Duke.entities.TaskList;
import Duke.Ui.Ui;

/**
 * Represents the Duke.Commands.DeadlineCommand of the Chat bot.
 */
public class DeadlineCommand extends Command {
    @Override
    public void processCommand() {
        System.out.println("abstract method invoked");
    }

    /**
     * Overloaded processCommand method from the abstract class Command.
     * Processes the command for a deadline Task.
     *
     * @param list The TaskList object that stores Tasks.
     * @param desc Description of the Deadline Task.
     * @param by dateline of the Deadline Task.
     * @param ui User interface of the Chat bot.
     */
    public void processCommand(TaskList list, String desc, String by, Ui ui) {
        Deadline deadline = new Deadline(desc, by);
        list.addTask(deadline);
        ui.print(String.format("Received, I've added the following deadlines:\n %s", deadline.toString()));
        ui.print(String.format("Now you have %d tasks in the list.", list.getSize()));
        ui.printDivider();
    }
}
