package Duke.Commands;

import Duke.entities.Task;
import Duke.entities.TaskList;
import Duke.Ui.Ui;

/**
 * Represents the Duke.Commands.ListCommand of the Chat bot.
 */
public class ListCommand extends Command {

    @Override
    public void processCommand() {
        System.out.println("abstract method invoked");
    }

    /**
     * Overloaded processCommand method from the abstract class Command.
     * Processes the command for a ListCommand.
     *
     * @param list The TaskList object that stores Tasks.
     * @param ui   User interface of the Chat bot.
     */
    public void processCommand(TaskList list, Ui ui) {
        for (int i = 0; i < list.getSize(); i++) {
            Task t = list.getTask(i);
            ui.print(String.format("%d.%s", i + 1, t.toString()));
        }
        ui.printDivider();
    }
}
