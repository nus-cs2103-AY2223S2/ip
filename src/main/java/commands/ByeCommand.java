package commands;

import static commands.CommandType.BYE;

import nook.Storage;
import nook.TaskList;
import nook.Ui;

/**
 * Represents the command that ends the conversation with Sirius.
 */
public class ByeCommand extends Command {

    /**
     * Constructs a new ByeCommand with the CommandType of BYE.
     */
    public ByeCommand() {
        super(BYE);
    }

    /**
     * Executes this ByeCommand with the specified TaskList, Ui, and Storage.
     * Informs the Ui to show the bye message.
     *
     * @param list the existing TaskList (not used in this command)
     * @param ui the Ui object to help display the bye message
     * @param storage the Storage storing the latest TaskList (not used in this command)
     */
    @Override
    public String execute(TaskList list, Ui ui, Storage storage) {
        return ui.getBye();
    }
}
