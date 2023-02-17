package DukeHelpfulCode.Commands;

import DukeHelpfulCode.Utilities.Storage;
import DukeHelpfulCode.Utilities.TaskList;
import DukeHelpfulCode.Utilities.UI;

public class HelpCommand extends Command{
    /** if dk what the user uinpuit justt thro whtis LMAO
     *
     */

    public HelpCommand(){}

    @Override
    public String execute(TaskList taskList){
        Command[] allCmd = {new HelpCommand(),
                new AddCommand(),
                new DeleteCommand(),
                new MarkCommand(),
                new ListCommand(),
                new ExitCommand()};
        String commandList = "Here's the list of commands:\n"; // just add the tostring of the commands
        for (int i = 0; i < allCmd.length; i++){
            commandList += i+1 + ". " + allCmd[i].toString();
        }
        return commandList;
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
