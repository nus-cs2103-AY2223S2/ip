package duke.commands;

import duke.database.Database;
import duke.exception.DukeException;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class ListCommand extends Command {

    public ListCommand() {
        super();
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Database database) throws DukeException {
        StringBuilder res = new StringBuilder(FRAME);
        for (int i = 0; i < taskList.length(); i++) {
            Task task = taskList.getTask(i + 1);
            res.append("     ").append(i + 1).append(". ").
                    append(task.status()).append("\n");
        }
        ui.response(res.append(FRAME).toString());
    }

}
