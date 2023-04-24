package twofive.command;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import twofive.data.TaskList;
import twofive.storage.Storage;
import twofive.task.Event;
import twofive.ui.TaskContainer;

/**
 * Adds a new Event task given a description, a start time and an end time
 * when command is executed.
 */
public class EventCommand extends Command {
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private String taskDescription;
    private String startTimeString;
    private String endTimeString;

    /**
     * Represents a constructor for the EventCommand class.
     *
     * @param taskDescription Description of the Event task.
     * @param startTimeString Start time of the Event task.
     * @param endTimeString End time of the Event task.
     */
    public EventCommand(String taskDescription, String startTimeString, String endTimeString) {
        this.taskDescription = taskDescription;
        this.startTimeString = startTimeString;
        this.endTimeString = endTimeString;
    }

    /**
     * Adds a new Event task given a description, a start time and an end time.
     * If task is added successfully, display success message.
     * If start time or end time given is not in yyyy-MM-dd HH:mm format,
     * display error message.
     *
     * @param tasks List of tasks to be added to.
     * @param storage Storage for saving or loading tasks.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        String commandResult = "";
        try {
            LocalDateTime startTime = LocalDateTime.parse(startTimeString, formatter);
            LocalDateTime endTime = LocalDateTime.parse(endTimeString, formatter);
            Event newEvent = new Event(taskDescription, startTime, endTime);

            //Adds new task to list of tasks
            tasks.addTask(newEvent);
            storage.save(tasks);
            TaskContainer.setTasks(tasks.getTasks());
            commandResult = "TwoFive has added this task for you:\n " + newEvent + "\n"
                    + "Now you have " + tasks.getTasksNum() + " tasks in the list";
        } catch (DateTimeParseException e) {
            commandResult = "Start time and end time must be in the format yyyy-MM-dd HH:mm, e.g. 2023-01-23 16:31";
        } catch (IOException e) {
            commandResult = e.getMessage();
        }
        return commandResult;
    }
}
