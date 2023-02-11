package duke.commands;

import duke.utils.DukeIo;
import duke.utils.Storage;
import duke.tasks.TaskList;

import java.io.IOException;

/**
 * QuitCommand class which handles operations on quit.
 */
public class QuitCommand extends Command {
    private final Storage STORAGE;
    /**
     * Public constructor for Mark/unmark command.
     * @param index User-input task index to mark/ unmark.
     */    
    public QuitCommand(Storage storage) {
        this.STORAGE = storage;
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
                STORAGE.saveFrom(TaskList.getAllTasks());
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