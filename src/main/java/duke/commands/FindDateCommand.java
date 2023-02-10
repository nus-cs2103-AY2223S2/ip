package duke.commands;
import java.util.ArrayList;

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
    public String execute(TaskList tasks, Storage storage) {
        try {
            String keyword = this.input.substring(9, input.length());
            ArrayList<Task> filtered = tasks.filterDate(keyword);
            assert filtered.size() > 0 : Ui.noTasksMessage();

            return Ui.printTasks(filtered);
        } catch (AssertionError ae) {
            return ae.getMessage();
        }
    }
}
