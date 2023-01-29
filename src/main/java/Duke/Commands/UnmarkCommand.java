package Duke.Commands;

import Duke.entities.Task;
import Duke.entities.TaskList;
import Duke.Ui.Ui;

/**
 * Represents the Duke.Commands.UnmarkCommand of the Chat bot.
 */
public class UnmarkCommand extends Command {
    @Override
    public void processCommand() {
        System.out.println("abstract method invoked");
    }

    /**
     * Overloaded processCommand method from the abstract class Command.
     * Processes the command for a TodoCommand.
     *
     * @param list The TaskList object that stores Tasks.
     * @param index The position of the Task in the TaskList to be unmarked.
     * @param ui User interface of the Chat bot.
     */
    public void processCommand(TaskList list, int index, Ui ui) {
        Task task = list.getTask(index);
        task.setUndone();
        ui.print(String.format("Nice, this task has been marked as done:\n %s", task.toString()));
        ui.printDivider();
    }
}
