package DukeHelpfulCode.Commands;

import DukeHelpfulCode.Exceptions.NoSuchTaskException;
import DukeHelpfulCode.Exceptions.TaskAlrMarkException;
import DukeHelpfulCode.Utilities.TaskList;

public class FindCommand extends Command{

    String[] keywords;

    public FindCommand(String[] keywords){
        this.keywords = keywords;
    }

    public FindCommand(){}

    @Override
    public String execute(TaskList taskList) throws TaskAlrMarkException, NoSuchTaskException {
        return taskList.find(keywords);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
