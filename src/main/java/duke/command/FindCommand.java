package duke.command;

import duke.exception.DukeException;
import duke.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.Ui;

public class FindCommand extends Command {
    private final String description;

    public FindCommand(String description) {
        this.description = description;
    }

    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) throws DukeException {
        TaskList newTasks = new TaskList();
        for (Task task: tasks) {
            if (task.isMatch(this.description)) {
                newTasks.add(task);
            }
        }
        ui.showAll(newTasks);
        return newTasks.toFormattedString();
    }
}
