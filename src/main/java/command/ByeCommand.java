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
        assert fileManager != null;
    }


    @Override
    public boolean isExit() {
        return true;
    }

     /**
      * Displays goodbye message
      *
      * @return
      * @throws DukeException
      */
    @Override
    public String executeCommand(TaskManager taskManager) throws DukeException {
        fileManager.saveTasksToFile(taskManager);
        return "ByeBye! Come play with me again!";
    }
}
