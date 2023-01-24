package command;

import java.util.ArrayList;

import exception.DukeException;
import task.Task;

public class UnmarkCommand extends Command {
    
    private ArrayList<Task> taskList;
    private String command;

    public UnmarkCommand(String command, ArrayList<Task> taskList) {
        this.command = command;
        this.taskList = taskList;
        
    }

    /*
     * Unmark takes in a String comman and handles the command
     * by checking for out of bounds as well as invalid syntaxes 
     * and unmarks the corresponding task as completed
     * @throws DukeException if input is incorrect
     */
    @Override
    public void execute() throws DukeException {

        String[] inputs = command.split(" ");
        if (inputs.length == 2) {
            int ind = Integer.parseInt(inputs[1]) - 1;
            if (ind >= taskList.size() || ind < 0) throw new DukeException("☹ OOPS!!! Invalid task number :(");

            taskList.get(ind).markUncompleted();

            System.out.println("    OK, I've marked this task as not done yet:");
            System.out.println("      " + taskList.get(ind));

        } else {
            throw new DukeException("Incorrect command: unmark <valid task index>");
        }
    }
}
