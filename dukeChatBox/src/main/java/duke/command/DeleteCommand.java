package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.IOException;
import java.util.ArrayList;

import static duke.task.TaskList.tryDeleteTask;

public class DeleteCommand extends Command {
    public DeleteCommand(String keyword, String statement) {
        this.keyword = keyword;
        this.statement = statement;
    }

    /**
     * Execute user's "delete" command and change the storage file accordingly.
     * @param taskList The list containing tasks.
     * @param ui dealing with interactions with the user.
     * @param storage The storage file of tasks.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ArrayList<Task> tasks = taskList.getTasks();
        tryDeleteTask(tasks, statement);
        try {
            storage.write(tasks);
        } catch (IOException e) {
            createDirectory(ui, storage, tasks);
        }
    }
}
