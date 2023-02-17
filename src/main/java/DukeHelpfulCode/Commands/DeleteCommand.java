package DukeHelpfulCode.Commands;

import DukeHelpfulCode.Exceptions.NoSuchTaskException;
import DukeHelpfulCode.Utilities.Storage;
import DukeHelpfulCode.Utilities.TaskList;
import DukeHelpfulCode.Utilities.UI;

import java.io.IOException;

public class DeleteCommand extends Command{

    int taskNum;

    public DeleteCommand(int taskNum){
        this.taskNum = taskNum;
    }
    public DeleteCommand(){};

    @Override
    public String execute(TaskList taskList) {
        String res;
        try {
            res = taskList.delete(taskNum);
        } catch (NoSuchTaskException e) {
            res = e.getMessage();
        }
        return res;
    }

    @Override
    public boolean isExit(){
        return false;
    }

    @Override
    public String toString() {
        return "delete -> Removes the specified Task from your list.\n  Format: delete <index>\n";
    }
}
