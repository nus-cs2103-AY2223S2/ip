package duke.command;

import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Represents a command that ends the session with the chatbot.
 */
public class ExitCommand extends Command {

    public ExitCommand() {
        super(true);
    }

    /**
     * Executes the command by signing off from the chatbot session.
     *
     * @param taskList The TaskList object that manages the list of Tasks.
     */
    @Override
    public void execute(TaskList taskList) {
        Ui.showWithNewLine("Bye. Have a nice day!");
    }

}
