package chattime.command;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import chattime.TaskList;
import chattime.storage.Storage;
import chattime.task.Task;
import chattime.ui.Ui;

/**
 * Represents ListCommand object that handles main logic of returning current task list items.
 */
public class ListCommand extends Command {

    private LocalDate requestedDate;

    /**
     * Creates ListCommand object to execute display list logic, with date search or without.
     *
     * @param date The specific date when relevant tasks take place, null if not specified.
     */
    public ListCommand(LocalDate date) {
        requestedDate = date;
    }

    /**
     * Implements and executes main logic of ListCommand object.
     * Assigns case into displayList and listTime according to the availability of requestedDate.
     *
     * @param ui The UI instance of bot.
     * @param taskList The current task list storing tasks.
     * @param storage The storage file to store current state items of task list.
     * @return The bot's reply to user's request list command.
     */
    @Override
    public String execute(Ui ui, TaskList taskList, Storage storage) {
        if (requestedDate == null) {
            return displayList(ui, taskList);
        } else {
            return listTime(ui, taskList);
        }
    }

    /**
     * Executes logic of displaying basic task list, without time search.
     *
     * @param ui The UI instance of bot.
     * @param taskList The current task list storing tasks.
     * @return The list of tasks.
     */
    private String displayList(Ui ui, TaskList taskList) {
        if (taskList.getList().size() == 0) {
            return ui.warnEmptyList();

        } else {
            return createListReply(taskList);
        }
    }

    private String createListReply(TaskList taskList) {
        int i = 1;
        String message = "Task(s) waiting to be completed:\n";

        for (Task task : taskList.getList()) {
            message = message.concat(String.format("\n     %d. %s", i, task));
            i++;
        }

        return message;
    }

    /**
     * Executes logic of displaying task list, filters task objects with time search.
     *
     * @param ui The UI instance of bot.
     * @param taskList The current task list storing tasks.
     * @return The list of tasks in queried time.
     */
    private String listTime(Ui ui, TaskList taskList) {
        if (taskList.getList().size() == 0) {
            return ui.warnEmptyList();

        } else {
            return createTimeListReply(taskList);
        }
    }

    private String createTimeListReply(TaskList taskList) {
        int i = 1;
        int total = 0;
        int pending = 0;
        String requestDate = requestedDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy "));
        String message = "SEE ~ I've found the task(s) that have deadlines / take place on "
                + requestDate + "for you:\n";

        for (Task task : taskList.getList()) {
            if (!task.getTaskStatus() && task.isOnDate(requestedDate)) {
                pending++;
            }
            if (task.isOnDate(requestedDate)) {
                message = message.concat(String.format("\n     %d. %s", i, task));
                i++;
                total++;
            }
        }

        message += "\n\nYou have " + total + " task(s) on this day. With " + pending + " task(s) to go.";

        return message;
    }
}
