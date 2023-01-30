package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * A command that stores the command to quit the chatbot. The action of adding the task can be carried out when called.
 */
public class ExitCommand extends Command {
    /**
     * Constructor for a command to exit the chatbot.
     *
     * @param COMMAND_STRING The exit command in string representation
     */
    public ExitCommand(String COMMAND_STRING) {
        super(Commands.EXIT, COMMAND_STRING);
    }

    /**
     * Exits the chatbot and shows the exit message.
     *
     * @param tasks   List of tasks that are stored
     * @param ui      UI to deal with the visual output
     * @param storage Storage to deal with input and output of data
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String exitMsg = "Thank you for coming!\n" + "Hope to see you again soon!\n" + "~~Bye";
        ui.showMsg(exitMsg);

        storage.updateData(tasks);
        ui.close();
    }
}
