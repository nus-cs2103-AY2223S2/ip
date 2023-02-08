package DukeHelpfulCode.Commands;

import DukeHelpfulCode.Tasks.*;
import DukeHelpfulCode.Utilities.*;

import java.io.FileWriter;
import java.io.IOException;

public class AddCommand extends Command{

    Task task;

    public AddCommand(Task task){
        this.task = task;
    }
    public AddCommand(){};

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws IOException {
        taskList.add(task);
        storage.write(taskList);
    };

    @Override
    public boolean isExit(){
        return false;
    }

    public String toString(){
        return "add -> Adds a Task to your list.\n  Format: add <Task Type> <TaskName> <DateTime>\n";
    }

}
