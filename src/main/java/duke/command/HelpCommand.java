package duke.command;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.textui.TextUi;

/**
 * A command that stores the command to show help on all the commands available. The action of showing the help text
 * can be carried out when called.
 */
public class HelpCommand extends Command {
    /**
     * Constructor for a command to show help texts.
     */
    public HelpCommand() {
        super(AvailableCommands.HELP);
    }

    /**
     * Constructor for a command to show help texts.
     *
     * @param taskList List of tasks that are stored
     * @param ui       UI to deal with the visual output
     * @param storage  Storage to deal with input and output of data
     * @return The string of what is printed out after execution
     */
    @Override
    public String execute(TaskList taskList, TextUi ui, Storage storage) throws DukeException {
        InputStream helpStream = this.getClass().getResourceAsStream("/values/help.txt");
        String helpText;

        assert helpStream != null;

        try {
            helpText = new String(helpStream.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new DukeException("Error occurred when reading help text file.");
        }

        return ui.showMsg(helpText);
    }
}
