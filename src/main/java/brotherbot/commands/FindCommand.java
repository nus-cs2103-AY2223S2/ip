package brotherbot.commands;

import brotherbot.storage.TaskList;
import brotherbot.ui.Ui;

import java.util.ArrayList;

public class FindCommand extends Command {

    /**
     * Constructor to create an FindCommand object.
     *
     * @param input Input string required for command execution.
     */
    public FindCommand(String input) {
        super(input);
    }

    /**
     * Executes command.
     *
     * @param storage Existing TaskList object required for command execution.
     * @param ui Ui object required for command execution.
     */
    public void execute(TaskList storage, Ui ui) {
        String keyword = this.input.substring(5);
        ui.toUser("Here are the matching task my brother!" );
        ArrayList<Integer> results = storage.search(keyword);
        for (Integer i: results) {
            ui.toUser((i + 1) + ". " + storage.get(i).toString());
        }
        ui.toUser("Anything else I can do for you top G" );
    }
}
