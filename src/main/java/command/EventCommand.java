package command;

import java.util.ArrayList;

import exception.DukeException;
import task.Event;
import task.Task;

public class EventCommand extends Command {
    
    private ArrayList<Task> taskList;
    private String command;

    public EventCommand(String command, ArrayList<Task> taskList) {
        this.command = command;
        this.taskList = taskList;
        
    }
    
    /*
     * adds event base on the string command
     * event requires taskName, StartDate and EndDate
     */
    @Override
    public void execute() throws DukeException {
        
        String taskName = getTaskName("event", command);
        String startDate = getStartDate(command);
        String endDate = getEndDate("event", command);

        Event event = new Event(taskName, startDate, endDate);
        taskList.add(event);

        System.out.println("    Got it. I've added this task:");
        System.out.println("      " + event);
        System.out.println("    Now you have " + taskList.size() + " tasks in the list.");
    }
}
