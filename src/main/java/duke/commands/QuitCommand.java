package duke.commands;

import java.io.IOException;

import duke.tasks.TaskList;
import duke.utils.DukeIo;
import duke.utils.Storage;

/**
 * QuitCommand class which handles operations on quit.
 */
public class QuitCommand extends Command {
    private final Storage storage;

    /**
     * Public constructor for quit command.
     * @param storage storage operator to execute clean up operations..
     */
    public QuitCommand(Storage storage) {
        this.storage = storage;
    }

    /**
     * Method to perform quit operations.
     *
     * @param dukeIo UI class used to return results
     * @param tasks TaskList containing all tasks to be marked/ unmarked.
     * @return UI feedback after successful save.
     */
    @Override
    public String exec(DukeIo dukeIo, TaskList tasks) {
        if (TaskList.getTaskCount() > 0) {
            try {
                storage.saveFrom(TaskList.getAllTasks());
            } catch (IOException e) {
                return dukeIo.notifySaveFailure();
            }
        }
        return dukeIo.printQuit();
    }

    /**
     * Checks that the command is an exit command.
     * @return true
     */
    @Override
    public boolean isExitCommand() {
        return true;
    }
}
