package command;

import task.TaskManager;
import util.DukeException;
import util.FileManager;

/**
 * Executes by command which terminates the chat.
 */
public class ByeCommand extends Command {

    private FileManager fileManager;
    public ByeCommand(FileManager fileManager) {
        this.fileManager = fileManager;
    }


    @Override
    public boolean isExit() {
        return true;
    }

     /**
     * Displays goodbye message
     *
     * @throws DukeException
     */
    @Override
    public void executeCommand(TaskManager taskManager) throws DukeException {
        System.out.println("ByeBye! Come play with me again!");
        fileManager.saveTasksToFile(taskManager);
    }
}
