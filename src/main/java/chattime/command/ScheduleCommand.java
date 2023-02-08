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
 * Represents ViewSchedulesCommand object that handles main logic of matching date with current tasks and return
 * sorted one-day-tasks.
 */
public class ViewSchedulesCommand extends Command {

    private LocalDate date;

    /**
     * Creates ViewSchedulesCommand object to filter and sort task list according to the date.
     *
     * @param input Specific date to sort relevant tasks.
     */
    public ViewSchedulesCommand(LocalDate input) {
        date = input;
    }

    /**
     * Executes logic of displaying sorted task list by filtering task objects on given date.
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
            StringBuilder message = new StringBuilder("I've scheduled the task(s) on "
                    + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy ")) + "for you:\n");

            Supplier<Stream<Task>> filteredTask = () -> taskList.getList().stream()
                    .filter(task -> task.isOnDate(date) && !task.getTaskStatus());

            for (LocalTime i = LocalTime.MIN; i.isBefore(LocalTime.MAX); i = i.plusHours(1)) {
                LocalTime currTime = i;

                message.append(currTime.format(DateTimeFormatter.ofPattern("hh:mm a :")));

                String taskSchedule = filteredTask.get()
                        .filter(task -> task.isOnTime(date, currTime)).map(Task::taskWithCode)
                        .reduce(" ", (prevTask, task) -> prevTask + "\n           ");

                message.append(taskSchedule);
            }

            int total = (int) filteredTask.get().count();
            message.append("\n     You have ").append(total).append(" unmarked task(s) on this day.");

            return message.toString();

        }
    }

}
