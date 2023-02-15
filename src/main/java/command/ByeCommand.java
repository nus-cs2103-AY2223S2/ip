package command;

import task.TaskManager;
import util.DukeException;
import util.DukeUI;
import util.FileManager;


/**
 * Executes bye command which saves all tasks to hard disk.
 */
public class ByeCommand extends Command {

    private FileManager fileManager;

    /**
     * Initialise bye command with the file manager to
     * store tasks in.
     *
     * @param fileManager
     */
    public ByeCommand(FileManager fileManager) {
        this.fileManager = fileManager;
        assert fileManager != null;
    }

    /**
     * Saves tasks into file manager and returns a
     * goodbye statement.
     *
     * @param taskManager
     * @return Goodbye message
     * @throws DukeException
     */
    @Override
    public String executeCommand(TaskManager taskManager) throws DukeException {
        fileManager.saveTasksToFile(taskManager);
        return DukeUI.terminateMessage();
    }
}
