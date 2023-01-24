package command;

import java.util.ArrayList;

import exception.DukeException;
import task.Task;
import task.ToDo;

public class TodoCommand extends Command {
    
    private ArrayList<Task> taskList;
    private String command;

    public TodoCommand(String command, ArrayList<Task> taskList) {
        this.command = command;
        this.taskList = taskList;
        
    }
    
    /*
     * adds todo base on the string command
     * todo only requires taskName
     */
    @Override
    public void execute() throws DukeException {
        ToDo toDo = new ToDo(getTaskName("todo", command));
        taskList.add(toDo);

        System.out.println("    Got it. I've added this task:");
        System.out.println("      " + toDo);
        System.out.println("    Now you have " + taskList.size() + " tasks in the list.");
    }
}
