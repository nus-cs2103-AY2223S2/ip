package DukeHelpfulCode.Commands;

import DukeHelpfulCode.Tasks.Task;
import DukeHelpfulCode.Utilities.Storage;
import DukeHelpfulCode.Utilities.TaskList;
import DukeHelpfulCode.Utilities.UI;

public class ExitCommand extends Command {

    public ExitCommand(){}

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        ui.exit();
    }

    @Override
    public boolean isExit(){
        return true;
    }

    @Override
    public String toString(){
        return "bye -> Exits DOOK.\n    Format: bye\n";
    }

}
