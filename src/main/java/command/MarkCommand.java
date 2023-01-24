package command;

import java.util.ArrayList;

import exception.DukeException;
import task.Task;

public class MarkCommand extends Command {
    
    private ArrayList<Task> taskList;
    private String command;

    public MarkCommand(String command, ArrayList<Task> taskList) {
        this.command = command;
        this.taskList = taskList;
        
    }
     /*
     * Mark takes in a String comman and handles the command
     * by checking for out of bounds as well as invalid syntaxes 
     * and marks the corresponding task as completed
     * @throws DukeException if input is incorrect
     */
    @Override
    public void execute() throws DukeException {
        String[] inputs = command.split(" ");

        if (inputs.length == 2) {
            int ind = Integer.parseInt(inputs[1]) - 1;
            // If index falls out of bounds 
            if (ind >= taskList.size() || ind < 0) throw new DukeException("☹ OOPS!!! Invalid task number :(");

            taskList.get(ind).markCompleted();

            System.out.println("    Nice! I've marked this task as done:");
            System.out.println("      " + taskList.get(ind));

        } else {
            throw new DukeException("Incorrect command: mark <valid task index>");
        }
    }
}
