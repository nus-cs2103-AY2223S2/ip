package rick.command;

import rick.TaskList;
import rick.Ui;

/**
 * Represents the command that searches for a task in the list by a given term.
 *
 * @author SeeuSim
 *         AY2223-S2 CS2103T
 */
public class FindCommand extends Command {
    private final String searchTerm;

    /**
     * Constructs this command with the search term.
     *
     * @param s The provided search term.
     */
    public FindCommand(String s) {
        this.searchTerm = s;
    }

    /**
     * Executes this command with the given TaskList and UI output, and
     * returns the UI to output to the user.
     *
     * @param ts The TaskList instance.
     * @param ui The Ui instance.
     */
    @Override
    public String execute(TaskList ts, Ui ui) {
        return ui.section(
                "Here are the matching tasks in your list:",
                ts.searchDescription(searchTerm)
        );
    }
}
