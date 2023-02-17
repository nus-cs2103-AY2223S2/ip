package DukeHelpfulCode.Commands;

import DukeHelpfulCode.Utilities.Storage;
import DukeHelpfulCode.Utilities.TaskList;
import DukeHelpfulCode.Utilities.UI;

public class ExitCommand extends Command {

    public ExitCommand(){}

    @Override
    public String execute(TaskList taskList) {
        return "Thanks for using DOOK. Hope you have a great day ahead!";
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
