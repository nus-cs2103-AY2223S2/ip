package duke.commands;
import java.util.ArrayList;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Task;


/**
 * The class for the Find command which extends Command class.
 */
public class FindCommand extends Command {
    private String input;

    /**
     * FindCommand constructor.
     *
     * @param input The user's input.
     */
    public FindCommand(String input) {
        this.input = input;
    }

    /**
     * @inheritDoc
     */
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        String[] words = this.input.trim().split(" ");
        if (words.length <= 1) {
            throw new DukeException(Ui.wrongFindCommandFormat());
        }
        String keyword = this.input.trim().substring(5, input.length());
        ArrayList<Task> filtered = tasks.filter(keyword);
        if (filtered.size() == 0) {
            return Ui.noTasksMessage();
        }

        return Ui.printTasks(filtered);
    }
}
