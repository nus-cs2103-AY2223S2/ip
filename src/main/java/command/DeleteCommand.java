package command;

import java.util.ArrayList;

import exception.DukeException;
import task.Task;

public class DeleteCommand extends Command {
    
    private ArrayList<Task> taskList;
    private String command;

    public DeleteCommand(String command, ArrayList<Task> taskList) {
        this.command = command;
        this.taskList = taskList;
        
    }

    /*
     * delete task by removing the Task at the corresponding index
     * throws exception for wrong syntax and invalid task number
     */
    @Override
    public void execute() throws DukeException {
        
        String[] inputs = command.split(" ");
        if (inputs.length == 2) {
            int ind = Integer.parseInt(inputs[1]) - 1;
            if (ind >= taskList.size() || ind < 0) throw new DukeException("☹ OOPS!!! Invalid task number :(");

            System.out.println("    Noted. I've removed this task:");
            System.out.println("      " + taskList.get(ind));

            taskList.remove(ind);

        } else {
            throw new DukeException("Incorrect command: delete <valid task index>");
        }
    }
}
