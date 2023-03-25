package brotherbot.commands;

import brotherbot.storage.TaskList;

public class DisplayCommand extends Command {

    /**
     * Constructor to create an DisplayCommand object.
     *
     * @param input Input string required for command execution.
     */
    public DisplayCommand(String input) {
        super(input);
    }

    /**
     * Executes command.
     *
     * @param storage Existing TaskList object required for command execution.
     */
    public String execute(TaskList storage) {
        String output;
        output = storage.display() + "\nAnything else I can do for you top G";
        return output;
    }
}
