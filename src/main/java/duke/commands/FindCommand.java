package duke.commands;

import duke.duke.Ui;
import duke.storage.Storage;
import duke.storage.TaskList;
import duke.tasks.Task;

import java.util.ArrayList;

/**
 * A command which searches the task list for a keyword.
 */
public class FindCommand extends Command {
    private final String query;
    public FindCommand(String query) {
        this.query = query;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        ArrayList<String> searchWords = new ArrayList<>();
        for (int i = 1; i <= tasks.size(); i++) {
            Task curr = tasks.get(i);
            if (curr.getDescription().contains(query)) {
                searchWords.add(String.format("%s. %s", i, curr.toString()));

            }

        }

        if (searchWords.isEmpty()) {
            ui.display("Unfortunately, nothing matches your search.");
        } else {
            String s = "Here are the matching tasks in your list: \n";
            for (int i = 0; i < searchWords.size(); i++) {
                s = s + searchWords.get(i) + "\n";
            }
            ui.display(s);
        }


    }


}
