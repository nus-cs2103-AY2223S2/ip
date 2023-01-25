package command;

import exception.DukeException;
import task.Task;
import task.TaskList;

public class ListCommand extends Command {
    
    private TaskList taskList;

    public ListCommand(TaskList taskList) {
        this.taskList = taskList;
    }

    /*
     * execute in printCommand iterates through the taskList 
     * and prints out the status of each individual tasks
     */
    @Override
    public void execute() throws DukeException {
        System.out.println("    Here are the tasks in your list: ");

        for (int i = 0; i < taskList.size(); i++) {
            Task toDo = taskList.get(i);

            System.out.println("    " + (i+1) + "." + toDo);

        }
    }
    
}
