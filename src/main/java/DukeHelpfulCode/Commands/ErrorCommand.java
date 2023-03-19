package DukeHelpfulCode.Commands;

import DukeHelpfulCode.Exceptions.NoSuchTaskException;
import DukeHelpfulCode.Exceptions.TaskAlrMarkException;
import DukeHelpfulCode.Utilities.TaskList;

public class ErrorCommand extends Command{

    String errorMessage;

    public ErrorCommand(String errorMessage){
        this.errorMessage = errorMessage;
    }

    @Override
    public String execute(TaskList taskList) throws TaskAlrMarkException, NoSuchTaskException {
        return "Error";
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
