package duke.commands;

import java.util.ArrayList;

import duke.components.Storage;
import duke.components.TaskList;
import duke.exceptions.DukeException;
import duke.tasks.Task;
/**
 * This is the FindCommand class to represent the find command parsed by Parser.
 * Encapsulates the information needed to print a list of tasks (matching the keyword provided) to the user.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Public constructor for a FindCommand
     * @param tokens arraylist representation of a tokenized user command
     * @throws DukeException when input is invalid
     */
    public FindCommand(ArrayList<String> tokens) throws DukeException {
        super(tokens);
        if (tokens.size() == 1) {
            throw new DukeException(
                    "Invalid input received! Please include a keyword to find!\n"
                    + "Find commands are in the form of: find keyword "
            );
        }
        keyword = String.join(" ", tokens.subList(1, tokens.size()));
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        ArrayList<Task> out = new ArrayList<>();
        for (int i = 1; i <= tasks.size(); i++) {
            if (tasks.getTask(i).containsString(keyword)) {
                out.add(tasks.getTask(i));
            }
        }
        if (out.size() == 0) {
            return "No tasks found!";
        } else {
            String res = "These are the " + out.size() + " tasks I found:";
            for (int i = 0; i < out.size(); i++) {
                res += "\n" + String.valueOf(i + 1) + ". " + out.get(i);
            }
            return res;
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
