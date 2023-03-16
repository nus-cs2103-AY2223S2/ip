package brotherbot.commands;

import brotherbot.storage.TaskList;
import brotherbot.ui.Ui;

public class MarkTaskCommand extends Command {

    /**
     * Constructor to create an AddTaskCommand object.
     *
     * @param input Input string required for command execution.
     */
    public MarkTaskCommand(String input) {
        super(input);
    }

    /**
     * Executes command.
     *
     * @param storage Existing TaskList object required for command execution.
     * @param ui Ui object required for command execution.
     */
    public void execute(TaskList storage, Ui ui) {
        int i = Integer.parseInt(input.substring(5)) - 1;
        storage.mark(i);
        ui.toUser("Marked as you wish my brother:");
        ui.toUser((i + 1) + ". " + storage.getPrintFormat(i));
    }
}
