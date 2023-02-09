package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.ArrayList;
import java.util.Comparator;

public class SortCommand extends Command {
    private final String taskType;

    public SortCommand(String taskType) {
        this.taskType = taskType;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        ArrayList<Task> filteredTasks = getFilteredTasks(tasks);
        filteredTasks.sort(Comparator.comparing(Task::getDate));
        return Ui.getSortOutput(filteredTasks);
    }

    private ArrayList<Task> getFilteredTasks(TaskList tasks) throws DukeException {
        if (taskType.equals("d")) {
            return tasks.getDeadlines();
        } else {
            return tasks.getEvents();
        }
    }
}
