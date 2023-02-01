package duke.commands;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.TextUi;
import org.w3c.dom.Text;

/**
 * Represents a mark command.
 */
public class MarkCommand extends Command {
    public static final String COMMAND_WORD = "mark";
    protected int taskNumber;

    /**
     * A constructor to initialize a mark command.
     *
     * @param taskNumber The task number of the task to be marked.
     */
    public MarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String execute(TaskList taskList, Storage storage, TextUi ui) {
        try {
            Task t = taskList.getTask(taskNumber - 1);
            if (t.isMarked()) {
                return ui.printError("Oops! This task has already been marked as done.");
            } else {
                t.mark();
                storage.save(taskList);
                return ui.printMarkedTask(t, taskList);
            }
        } catch (IndexOutOfBoundsException e) {
            return ui.printError("Huh... the task does not exist.");
        }

    }
}
