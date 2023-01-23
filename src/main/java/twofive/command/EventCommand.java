package twofive.command;

import twofive.data.TaskList;
import twofive.storage.Storage;
import twofive.task.Event;
import twofive.ui.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class EventCommand extends Command {
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private String taskDescription;
    private String startTimeString;
    private String endTimeString;

    public EventCommand(String taskDescription, String startTimeString, String endTimeString) {
        this.taskDescription = taskDescription;
        this.startTimeString = startTimeString;
        this.endTimeString = endTimeString;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            LocalDateTime startTime = LocalDateTime.parse(startTimeString, formatter);
            LocalDateTime endTime = LocalDateTime.parse(endTimeString, formatter);
            Event newEvent = new Event(taskDescription, startTime, endTime);

            //Adds new task to list of tasks
            tasks.addTask(newEvent);
            ui.showMesssage("Got it. I've added this task:\n " + newEvent + "\n"
                    + "Now you have " + tasks.getTasksNum() + " tasks in the list");
        } catch (DateTimeParseException e) {
            ui.showError("Start time and end time must be in the format yyyy-MM-dd HH:mm, e.g. 2023-01-23 16:31");
        }
    }
}
