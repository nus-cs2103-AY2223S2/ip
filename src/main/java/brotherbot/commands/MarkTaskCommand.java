package brotherbot.commands;

import brotherbot.storage.TaskList;

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
     */
    public String execute(TaskList storage) {
        int i = Integer.parseInt(input.substring(5)) - 1;
        storage.mark(i);
        String output = "Marked as you wish my brother:\n" + ((i + 1) + ". " + storage.getPrintFormat(i));
        return output;
    }
}
