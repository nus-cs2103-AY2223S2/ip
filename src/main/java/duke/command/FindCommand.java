package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
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

        if (taskList.getTotalTasks() == 0) {
            ui.showText("There are no tasks for me to search, Sir!");
        } else {
            ui.showText("Let me try to search your tasks, Sir:");

            taskList.getAllTasks().stream()
                    .filter(task -> task.getDescription().contains(textToFind))
                    .forEach(task -> ui.showText(String.format("%s", task)));

            ui.showText("My search has completed, Sir!");
        }

        ui.showLine();
    }
}
