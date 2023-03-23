package brotherbot.commands;

import brotherbot.storage.Task;
import brotherbot.storage.TaskList;


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
    public String execute(TaskList storage) {
        int i = Integer.parseInt(input.substring(7)) - 1;
        Task removed = storage.get(i);
        storage.remove(i);
        String output = "Deleted this task for you my brother:\n" + removed.toString()+ "\nNow you have " + storage.size() + " tasks left";
        return output;
    }
}
