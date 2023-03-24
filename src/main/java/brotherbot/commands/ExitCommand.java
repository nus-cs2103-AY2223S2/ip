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
        super.isExit = true;
    }

    /**
     * Executes command.
     *
     * @param storage Existing TaskList object required for command execution.
     */
    public String execute(TaskList storage) {
        String output = "bye brother hope you had a good day!";
        return output;
    }
}
