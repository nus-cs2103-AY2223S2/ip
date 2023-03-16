package brotherbot.commands;

import brotherbot.storage.TaskList;
import brotherbot.storage.Task;
import brotherbot.ui.Ui;


public class DeleteCommand extends Command {

    /**
     * Constructor to create an DeleteCommand object.
     *
     * @param input Input string required for command execution.
     */
    public DeleteCommand(String input) {
        super(input);
    }

    /**
     * Executes command.
     *
     * @param storage Existing TaskList object required for command execution.
     * @param ui Ui object required for command execution.
     */
    public void execute(TaskList storage, Ui ui) {
        int i = Integer.parseInt(input.substring(7)) - 1;
        Task removed = storage.get(i);
        storage.remove(i);
        ui.toUser("Deleted this task for you my brother:\n" + removed.toString());
        ui.toUser("Now you have " + storage.size() + " tasks left");
    }
}
