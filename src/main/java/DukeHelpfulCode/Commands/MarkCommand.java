package DukeHelpfulCode.Commands;

import java.io.FileWriter;

import DukeHelpfulCode.Exceptions.NoSuchTaskException;
import DukeHelpfulCode.Exceptions.TaskAlrMarkException;
import DukeHelpfulCode.Utilities.Storage;
import DukeHelpfulCode.Utilities.TaskList;
import DukeHelpfulCode.Utilities.UI;

import java.io.IOException;

public class MarkCommand extends Command{

    boolean isMark;
    int taskNum;

    public MarkCommand(boolean isMark, int taskNum){
        this.isMark = isMark;
        this.taskNum = taskNum;
    }
    public MarkCommand(){};

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws TaskAlrMarkException, NoSuchTaskException, IOException {
        taskList.mark(isMark, taskNum);
        storage.write(taskList);
    };

    @Override
    public boolean isExit(){
        return false;
    }

    @Override
    public String toString(){
        return "mark / unmark -> Marks / Unmarks the specified Task\n   Format: mark/unmark <index>\n";
    }
}
