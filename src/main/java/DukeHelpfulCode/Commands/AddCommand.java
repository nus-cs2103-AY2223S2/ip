package DukeHelpfulCode.Commands;

import DukeHelpfulCode.Tasks.*;
import DukeHelpfulCode.Utilities.*;

import java.io.IOException;

public class AddCommand extends Command{

    Task task;

    public AddCommand(Task task){
        this.task = task;
    }
    public AddCommand(){};

    @Override
    public String execute(TaskList taskList) {
        return taskList.add(task);
    };

    @Override
    public boolean isExit(){
        return false;
    }

    public String toString(){
        return "add -> Adds a Task to your list.\n  Format: add <Task Type> <TaskName> <DateTime>\n";
    }

}
