package Nerd.Commands;

import Nerd.entities.Deadline;
import Nerd.entities.TaskList;
import Nerd.Ui.Ui;

/**
 * Represents the Duke.Commands.DeadlineCommand of the Chat bot.
 */
public class DeadlineCommand extends Command {

    /**
     * Overloaded processCommand method from the abstract class Command.
     * Processes the command for a deadline Task.
     *
     * @param list The TaskList object that stores Tasks.
     * @param desc Description of the Deadline Task.
     * @param by   dateline of the Deadline Task.
     * @param ui   User interface of the Chat bot.
     */
    public String processCommand(TaskList list, String desc, String by, Ui ui) {
        Deadline deadline = new Deadline(desc, by);
        list.addTask(deadline);
        return String.format("Received, I've added the following deadlines:\n %s\nNow you have %d tasks in the list.", deadline.toString());
    }
}
