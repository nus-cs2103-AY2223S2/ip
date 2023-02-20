package commands;

import static commands.CommandType.HELP;

import nook.Storage;
import nook.TaskList;
import nook.Ui;

/**
 * Represents the command that provides user with link to the user guide.
 */
public class HelpCommand extends Command {
    private static final String userGuideLink = "https://github.com/kimberlybp/ip/blob/master/docs/README.md";

    /**
     * Constructs a new HelpCommand with the CommandType of HELP.
     */
    public HelpCommand() {
        super(HELP);
    }

    /**
     * Executes this HelpCommand with the specified TaskList, Ui, and Storage.
     * Informs the Ui to show the link to the user guide.
     *
     * @param list the existing TaskList (not used in this command)
     * @param ui the Ui object to help display the bye message
     * @param storage the Storage storing the latest TaskList (not used in this command)
     */
    @Override
    public String execute(TaskList list, Ui ui, Storage storage) {
        return "Ah, here's the user guide link you'll need to get started with"
                + " the commands I can recognize and assist you with:\n\n"
                + userGuideLink + "\n\nIt should provide all the information "
                + "you need to navigate and use the app with ease.";
    }
}
