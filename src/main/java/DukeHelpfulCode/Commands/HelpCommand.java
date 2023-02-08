package DukeHelpfulCode.Commands;

import DukeHelpfulCode.Tasks.Task;
import DukeHelpfulCode.Utilities.Storage;
import DukeHelpfulCode.Utilities.TaskList;
import DukeHelpfulCode.Utilities.UI;

public class HelpCommand extends Command{
    /** if dk what the user uinpuit justt thro whtis LMAO
     *
     */

    public HelpCommand(){}

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage){
        ui.showCommands();
    };

    @Override
    public boolean isExit(){
        return false;
    }

    @Override
    public String toString(){
        return "help -> Displays the list of commands.\n    Format: help\n";
    }
}
