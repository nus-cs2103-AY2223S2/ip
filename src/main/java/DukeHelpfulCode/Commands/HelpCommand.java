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
        return "Here is the list of Commands:\n"
                + "add <Task Type> <Task>: Adds the task to the list\n"
                + "mark / unmark <index>: Marks the index-th item as done/not done\n"
                + "delete <index>: Deletes the index-th item\n"
                + "list: Shows the current task list\n"
                + "bye: saves the task list and exits DOOK\n"
                + "help: shows the list of commands\n";
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
