package duke.command;

import duke.tasklist.TaskList;

/**
 * Represents a command that ends the session with the chatbot.
 */
public class ExitCommand extends Command {

    public ExitCommand() {
        super(true);
    }

    @Override
    public void execute(TaskList taskList) {
        System.out.println("Bye. Have a nice day!");
    }

}
