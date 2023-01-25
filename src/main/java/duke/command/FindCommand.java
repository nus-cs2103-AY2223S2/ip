package duke.command;

import duke.TaskList;
import duke.Ui;

/**
 * The command to find a task in the list by a given term.
 *
 * @author SeeuSim
 * AY2223-S2 CS2103T
 */
public class FindCommand extends Command {
    private final String searchTerm;

    /**
     * Initialise this command with the search term.
     * @param s The provided search term.
     */
    public FindCommand(String s) {
        this.searchTerm = s;
    }

    /**
     * Execute this task.
     * @param ts The TaskList instance.
     * @param ui The Ui instance.
     */
    @Override
    public void execute(TaskList ts, Ui ui) {
        ui.section(
                "Here are the matching tasks in your list:\n" +
                        ts.searchDescription(searchTerm)
        );
    }
}
