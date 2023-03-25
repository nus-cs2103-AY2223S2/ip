package brotherbot.commands;

import brotherbot.storage.TaskList;

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
     */
    public String execute(TaskList storage) {
        String keyword = this.input.substring(5);
        String output = "Here are the matching task(s) my brother!";
        ArrayList<Integer> results = storage.search(keyword);
        if (results.isEmpty()) {
            output = "No matches found brother, try again!";
            return output;
        }
        for (Integer i : results) {
            output = output + "\n" + (i + 1) + ". " + storage.get(i).toString();
        }
        return output;
    }

}
