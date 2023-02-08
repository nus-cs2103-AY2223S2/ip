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

    @Override
    public void run(Ui ui, TaskList taskList, Storage storage, String... args) throws DukeException {
        String textToFind = args[0];

        ui.showLine();

        ui.showText("Here are the matching tasks in your list:");

        taskList.getAllTasks().stream()
                .filter(task -> task.getDescription().contains(textToFind))
                .forEach(task -> ui.showText(String.format("%s", task)));

        ui.showLine();
    }
}
