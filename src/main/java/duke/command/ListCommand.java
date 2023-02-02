package duke.command;

import duke.data.TaskList;
import duke.data.TypeOfTask;
import duke.action.Task;
import duke.storage.Storage;
import duke.ui.Ui;

public class ListCommand extends Command {
    
    public ListCommand(String[] contents) {
        super(contents, false);
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.displayResult(TypeOfTask.list, null, taskList);
        for(int i=0; i < taskList.getSize();i++){
            Task currentTask = taskList.getTaskByIndex(i);
            System.out.println(String.format("%d. %s",i+1,currentTask.toString()));
        }
    }
}
