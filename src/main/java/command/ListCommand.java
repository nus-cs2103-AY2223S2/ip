package command;

import exception.DukeException;
import task.Task;
import task.TaskList;
import util.Ui;

public class ListCommand extends Command {
    
    private TaskList taskList;
    private Ui ui;

    public ListCommand(TaskList taskList, Ui ui) {
        this.taskList = taskList;
        this.ui = ui;
    }

    /*
     * execute in printCommand iterates through the taskList 
     * and prints out the status of each individual tasks
     */
    @Override
    public boolean execute() throws DukeException {
        ui.printList(taskList);

        return false;
    }
}
