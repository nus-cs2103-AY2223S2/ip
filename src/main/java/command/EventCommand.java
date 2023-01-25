package command;

import java.time.format.DateTimeParseException;
import exception.DukeException;
import task.Event;
import task.TaskList;
import util.Ui;

public class EventCommand extends Command {
    private String command;
    private TaskList taskList;
    private Ui ui;

    public EventCommand(String command, TaskList taskList, Ui ui) {
        this.command = command;
        this.taskList = taskList;
        this.ui = ui;
    }
    
    /*
     * adds event base on the string command
     * event requires taskName, StartDate and EndDate
     */
    @Override
    public boolean execute() throws DukeException, DateTimeParseException {
        
        String taskName = getTaskName("event", command);
        String startDate = getStartDate(command);
        String endDate = getEndDate("event", command);

        Event event = new Event(taskName, startDate, endDate);
        taskList.add(event);

        ui.printAddedTask(event, taskList);
        return false;
    }
}
