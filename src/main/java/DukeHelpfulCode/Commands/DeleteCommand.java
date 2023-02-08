package DukeHelpfulCode.Commands;

import DukeHelpfulCode.Exceptions.NoSuchTaskException;
import DukeHelpfulCode.Tasks.Task;
import DukeHelpfulCode.Utilities.Storage;
import DukeHelpfulCode.Utilities.TaskList;
import DukeHelpfulCode.Utilities.UI;

import java.io.FileWriter;
import java.io.IOException;

public class DeleteCommand extends Command{

    int taskNum;

    public DeleteCommand(int taskNum){
        this.taskNum = taskNum;
    }
    public DeleteCommand(){};

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws IOException {
        try {
            taskList.delete(taskNum);
            storage.write(taskList);
        } catch (NoSuchTaskException e) {
            // do nothing, move onto next command
        }
    };

    @Override
    public boolean isExit(){
        return false;
    }

    @Override
    public String toString() {
        return "delete -> Removes the specified Task from your list.\n  Format: delete <index>\n";
    }
}
