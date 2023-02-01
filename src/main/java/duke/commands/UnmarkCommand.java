package duke.commands;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.TextUi;
import org.w3c.dom.Text;

/**
 * Represents an unmark command.
 */
public class UnmarkCommand extends Command {
    public static final String COMMAND_WORD = "unmark";
    protected int taskNumber;

    /**
     * A constructor to initialize an unmark command.
     *
     * @param taskNumber The task number of the task to be unmarked.
     */
    public UnmarkCommand(int taskNumber) {
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
            if (!t.isMarked()) {
                return ui.printError("Oops! This task has not been marked as done before.");
            } else {
                t.unMark();
                storage.save(taskList);
                return ui.printUnmarkedTask(t, taskList);
            }
        } catch (IndexOutOfBoundsException e) {
            return ui.printError("Huh... the task does not exist.");
        }
    }

}
