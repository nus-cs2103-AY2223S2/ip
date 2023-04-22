package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.ArrayList;

import static duke.task.TaskList.findTask;

public class FindCommand extends Command{

    String statement;

    public FindCommand(String statement) {
        this.statement = statement;
    }

    /**
     * Execute user's "find" command.
     * @param taskList The list containing tasks.
     * @param ui dealing with interactions with the user.
     * @param storage The storage file of tasks.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ArrayList<Task> tasks = taskList.getTasks();
        findTask(tasks, statement);
    }
}
