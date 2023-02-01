package duke.commands;

import duke.database.Database;
import duke.exception.DukeException;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.util.ArrayList;

public class FindCommand extends Command{

    private final String query;

    public FindCommand(String query) {
        this.query = query;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Database database) throws DukeException {
        ArrayList<Task> tasks = taskList.getTaskList();
        StringBuilder queriedTasks = new StringBuilder(FRAME);
        int numOfQueriedTasks = 0;
        for (Task task : tasks) {
            if (task.details.contains(this.query)) {
                queriedTasks.append("     ").append(numOfQueriedTasks + 1).append(". ").
                        append(task.status()).append("\n");
                numOfQueriedTasks++;
            }
        }
        ui.response(queriedTasks.append(FRAME).toString());
    }
}
