package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.IOException;
import java.util.ArrayList;

import static duke.task.TaskList.tryAddDeadline;

public class DeadlineCommand extends Command {
    public DeadlineCommand(String keyword, String statement) {
        this.keyword = keyword;
        this.statement = statement;
    }

    /**
     * Execute user's "deadline" command and change the storage file accordingly.
     * @param taskList The list containing tasks.
     * @param ui dealing with interactions with the user.
     * @param storage The storage file of tasks.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ArrayList<Task> tasks = taskList.getTasks();
        tryAddDeadline(tasks, statement);
        try {
            storage.write(tasks);
        } catch (IOException e) {
            createDirectory(ui, storage, tasks);
        }
    }
}
