package duke.commands;
import java.util.ArrayList;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Task;

/**
 * The class for the FindDate command which extends Command class.
 */
public class FindDateCommand extends Command {
    private String input;

    /**
     * FindDateCommand constructor.
     *
     * @param input The user's input.
     */
    public FindDateCommand(String input) {
        this.input = input;
    }

    /**
     * @inheritDoc
     */
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        String[] words = this.input.trim().split(" ");
        if (words.length <= 1) {
            throw new DukeException(Ui.wrongFindDateCommand());
        }
        String keyword = this.input.trim().substring(9, input.length());
        ArrayList<Task> filtered = tasks.filterDate(keyword.toLowerCase());
        System.out.println(filtered.size());
        if (filtered.size() == 0) {
            return Ui.noTasksMessage();
        }

        return Ui.printTasks(filtered);
    }
}
