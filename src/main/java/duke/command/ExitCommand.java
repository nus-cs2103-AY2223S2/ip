package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.textui.TextUi;

/**
 * A command that stores the command to quit the chatbot. The action of exiting the chatbot can be carried out when
 * called.
 */
public class ExitCommand extends Command {

    public static final String EXIT_MSG = "Thank you for coming!\n" + "Hope to see you again soon!\n" + "~~Bye";

    /**
     * Constructor for a command to exit the chatbot.
     */
    public ExitCommand() {
        super(AvailableCommands.EXIT);
    }

    /**
     * Exits the chatbot and shows the exit message.
     *
     * @param tasklist List of tasks that are stored
     * @param ui       UI to deal with the visual output
     * @param storage  Storage to deal with input and output of data
     * @return The string of what is printed out after execution
     */
    @Override
    public String execute(TaskList tasklist, TextUi ui, Storage storage) throws DukeException {
        String output = ui.showMsg(EXIT_MSG);

        storage.updateData(tasklist);
        ui.close();

        return output;
    }
}
