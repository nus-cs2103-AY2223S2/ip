package chattime.command;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.function.Supplier;
import java.util.stream.Stream;

import chattime.TaskList;
import chattime.storage.Storage;
import chattime.task.Task;
import chattime.ui.Ui;

/**
 * Represents ScheduleCommand object that handles main logic of matching date with current tasks and returns
 * sorted one-day-tasks.
 */
public class ScheduleCommand extends Command {

    private LocalDate date;

    /**
     * Creates ScheduleCommand object to filter and sorts task list according to the date.
     *
     * @param input The specific date to sort relevant tasks.
     */
    public ScheduleCommand(LocalDate input) {
        date = input;
    }

    /**
     * Executes logic of displaying sorted task list by filtering task objects on given date.
     * Only event and deadline tasks are scheduled.
     * Schedules event by time span, schedules deadline on time before end time.
     *
     * @param ui The UI instance of bot.
     * @param taskList The current task list storing tasks.
     * @param storage The storage file to store current state items of task list.
     * @return The bot's reply to user's view schedules command.
     */
    @Override
    public String execute(Ui ui, TaskList taskList, Storage storage) {
        if (taskList.getList().size() == 0) {
            return ui.warnEmptyList();
        } else {
            StringBuilder message = new StringBuilder("Look! These things are happening on  "
                    + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy ")) + ":\n");

            Supplier<Stream<Task>> filteredTask = () -> taskList.getList().stream()
                    .filter(task -> task.isOnDate(date) && !task.getTaskStatus());

            message.append(scheduleContent(filteredTask));

            int total = (int) filteredTask.get().count();
            message.append("\n\nYou have ").append(total).append(" unmarked task(s) on this day");

            return message.toString();

        }
    }

    private StringBuilder scheduleContent(Supplier<Stream<Task>> filteredTask) {
        LocalTime time = LocalTime.MIN;
        StringBuilder scheduledTasks = new StringBuilder();
        for (int i = 0; i < 24; i++) {
            LocalTime currTime = time;
            time = time.plusHours(1);

            String taskSchedule = filteredTask.get()
                    .filter(task -> task.isOnTime(date, currTime)).map(Task::taskWithCode)
                    .reduce("", (prevTask, task) -> prevTask + "\n           " + task);

            scheduledTasks.append(currTime.format(DateTimeFormatter.ofPattern("\nhh:mm a :")));
            scheduledTasks.append(taskSchedule);
        }
        return scheduledTasks;
    }

}
