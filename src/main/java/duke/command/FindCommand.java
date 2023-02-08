package duke.command;

import java.util.List;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Command to find task(s).
 */
public class FindCommand implements Command {
    @Override
    public String getCommandName() {
        return "find";
    }

    @Override
    public String getCommandRegexPattern() {
        return "^find (.*)$";
    }

    @Override
    public String getCommandPattern() {
        return "find <text>";
    }

    /**
     * Find tasks containing text.
     *
     * @param ui       User interface.
     * @param taskList Task list.
     * @param storage  Storage.
     * @param args     Argument list in order: text to find.
     * @throws DukeException If failed to save new task list to storage or invalid date time
     *                       format.
     */
    @Override
    public void run(Ui ui, TaskList taskList, Storage storage, String... args) throws DukeException {
        // Assert arguments has only 1 item: text to find.
        assert args.length == 1;

        String textToFind = args[0];

        ui.showLine();

        ui.showText("Here are the matching tasks in your list:");

        List<Task> tasks = taskList.getAllTasks();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.getDescription().contains(textToFind)) {
                ui.showText(String.format("%d.%s", i + 1, task));
            }
        }

        ui.showLine();
    }
}
