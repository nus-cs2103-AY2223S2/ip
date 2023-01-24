package chattime.command;

import chattime.storage.Storage;
import chattime.task.Task;
import chattime.TaskList;
import chattime.ui.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
/**
 * Represents ListCommand object that handles main logic of returning current task list items.
 */
public class ListCommand extends Command {

    private LocalDate requestDate;

    /**
     * Creates ListCommand object to execute display list logic, with date search or without.
     *
     * @param date Specific date when relevant tasks take place, null if not specified.
     */
    public ListCommand(LocalDate date) {
        requestDate = date;
    }

    /**
     * Implements and executes main logic of ListCommand object.
     * Assign cases into displayList and listTime.
     *
     * @param ui UI instance of bot.
     * @param taskList Current task list storing tasks.
     * @param storage Storage file to store current state items of task list.
     */
    @Override
    public void execute(Ui ui, TaskList taskList, Storage storage) {
        if (requestDate == null) {
            displayList(ui, taskList);
        } else {
            listTime(ui, taskList);
        }
    };

    /**
     * Executes logic of displaying basic task list, without time search.
     *
     * @param ui UI instance of bot.
     * @param taskList Current task list storing tasks.
     */
    private void displayList(Ui ui, TaskList taskList) {
        if (taskList.getList().size() == 0) {
            ui.emptyList();
        } else {
            int i = 1;
            String message = "chattime.task.Task(s) waiting to be completed:";

            for (Task task : taskList.getList()) {
                message = message.concat(String.format("\n     %d. %s", i, task));
                i++;
            }

            ui.replyUser(message);
        }
    }

    /**
     * Executes logic of displaying task list, filters task objects with time search.
     *
     * @param ui UI instance of bot.
     * @param taskList Current task list storing tasks.
     */
    private void listTime(Ui ui, TaskList taskList) {
        if (taskList.getList().size() == 0) {
            ui.emptyList();
        } else {
            int i = 1, total = 0, pending = 0;
            String message = "I've sorted the task(s) that have deadlines / take place on " +
                    requestDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy ")) + "for you:";

            for (Task task : taskList.getList()) {
                if (task.onDate(requestDate)) {
                    message = message.concat(String.format("\n     %d. %s", i, task));
                    i++;
                    total++;
                    if (!task.getTaskStatus()) {
                        pending++;
                    }
                }
            }

            message += "\n     You have " + total + " task(s) on this day. With " + pending + " task(s) to go.";

            ui.replyUser(message);
        }
    }
}
