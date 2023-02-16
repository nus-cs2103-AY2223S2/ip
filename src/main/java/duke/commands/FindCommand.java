package duke.commands;


import java.util.ArrayList;

import duke.duke.Ui;
import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.storage.TaskList;
import duke.tasks.Task;


/**
 * A command which searches the task list for a keyword.
 */
public class FindCommand extends Command {
    private final String query;
    public FindCommand(String query) {
        this.query = query;
    }

    /**
     * Searches for tasks which contain the keyword specified by the user.
     * Displays these tasks with the help of the UI.
     * @param tasks
     * @param ui
     * @param storage
     * @throws Exception
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ArrayList<String> searchWords = new ArrayList<>();
        for (int i = 1; i <= tasks.size(); i++) {
            Task curr = tasks.get(i);
            if (curr.getDescription().contains(query)) {
                searchWords.add(String.format("%s. %s", i, curr.toString()));
            }
        }
        String message;
        if (searchWords.isEmpty()) {
            message = "Unfortunately, nothing matches your search. Beep :(";
            ui.display(message);
            return message;
        } else {
            String s = "Boop :). Here are the matching tasks in your list: \n";
            for (int i = 0; i < searchWords.size(); i++) {
                s = s + searchWords.get(i) + "\n";
            }
            ui.display(s);
            return s;
        }


    }


}
