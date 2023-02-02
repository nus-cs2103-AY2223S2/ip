package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.List;

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
