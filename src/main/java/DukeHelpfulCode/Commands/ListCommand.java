package DukeHelpfulCode.Commands;

import DukeHelpfulCode.Utilities.Storage;
import DukeHelpfulCode.Utilities.TaskList;
import DukeHelpfulCode.Utilities.UI;

public class ListCommand extends Command{

    public ListCommand(){}

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        System.out.println(taskList.toString());
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
