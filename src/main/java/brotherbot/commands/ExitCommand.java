package brotherbot.commands;

import brotherbot.storage.TaskList;

public class ExitCommand extends Command {

    /**
     * Constructor to create an ExitCommand object.
     *
     * @param input Input string required for command execution.
     */
    public ExitCommand(String input) {
        super(input);
    }

    /**
     * Executes command.
     *
     * @param storage Existing TaskList object required for command execution.
     */
    public String execute(TaskList storage) {
        String output = "ok see you brother all love no cringe!";
        return output;
    }
}
