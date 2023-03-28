package DukeHelpfulCode.Commands;

import DukeHelpfulCode.Utilities.Storage;
import DukeHelpfulCode.Utilities.TaskList;
import DukeHelpfulCode.Utilities.UI;

public class ListCommand extends Command{

    public ListCommand(){}

    @Override
    public String execute(TaskList taskList) {
        return taskList.toString();
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String toString(){
        return "list -> Displays your list.\n   Format: list\n";
    }
}
