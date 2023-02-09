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
 * Represents ScheduleCommand object that handles main logic of matching date with current tasks and return
 * sorted one-day-tasks.
 */
public class ScheduleCommand extends Command {

    private LocalDate date;

    /**
     * Creates ScheduleCommand object to filter and sort task list according to the date.
     *
     * @param input Specific date to sort relevant tasks.
     */
    public ScheduleCommand(LocalDate input) {
        date = input;
    }

    /**
     * Executes logic of displaying sorted task list by filtering task objects on given date.
     * Only event and deadline tasks are scheduled.
     * Event is scheduled to time span, deadline is scheduled before its end.
     *
     * @param ui UI instance of bot.
     * @param taskList Current task list storing tasks.
     * @param storage Storage file to store current state items of task list.
     * @return Bot's reply to user's view schedules command.
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

            LocalTime time = LocalTime.MIN;
            for (int i = 0; i < 24; i++) {
                LocalTime currTime = time;
                time = time.plusHours(1);

                message.append(currTime.format(DateTimeFormatter.ofPattern("\nhh:mm a :")));

                String taskSchedule = filteredTask.get()
                        .filter(task -> task.isOnTime(date, currTime)).map(Task::taskWithCode)
                        .reduce("", (prevTask, task) -> prevTask + "\n           " + task);

                message.append(taskSchedule);
            }

            int total = (int) filteredTask.get().count();
            message.append("\nYou have ").append(total).append(" unmarked task(s) on this day :(");

            return message.toString();

        }
    }

}
