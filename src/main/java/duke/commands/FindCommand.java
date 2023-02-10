package duke.commands;
import java.util.ArrayList;

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
    public String execute(TaskList tasks, Storage storage) {
        try {
            String keyword = this.input.substring(5, input.length());
            ArrayList<Task> filtered = tasks.filter(keyword);
            assert filtered.size() > 0 : Ui.noTasksMessage();

            return Ui.printTasks(filtered);
        } catch (AssertionError ae) {
            return ae.getMessage();
        }
    }
}
