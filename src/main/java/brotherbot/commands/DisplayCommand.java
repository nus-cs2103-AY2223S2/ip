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
        output = ("Here are your existing tasks my brother!" ) + storage.display();
        output = output + "\nAnything else I can do for you top G";
        return output;
    }
}
