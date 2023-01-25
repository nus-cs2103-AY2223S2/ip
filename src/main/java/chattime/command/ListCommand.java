package chattime.command;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import chattime.TaskList;
import chattime.storage.Storage;
import chattime.task.Task;
import chattime.ui.Ui;

public class ListCommand extends Command {

    private LocalDate requestedDate;

    public ListCommand(LocalDate date) {
        requestedDate = date;
    }

    @Override
    public void execute(Ui ui, TaskList taskList, Storage storage) {
        if (requestedDate == null) {
            displayList(ui, taskList);
        } else {
            listTime(ui, taskList);
        }
    };

    public void displayList(Ui ui, TaskList taskList) {
        if (taskList.getList().size() == 0) {
            ui.warnEmptyList();

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

    public void listTime(Ui ui, TaskList taskList) {
        if (taskList.getList().size() == 0) {
            ui.warnEmptyList();

        } else {
            int i = 1;
            int total = 0;
            int pending = 0;
            String message = "I've sorted the task(s) that have deadlines / take place on "
                    + requestedDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy ")) + "for you:";

            for (Task task : taskList.getList()) {
                if (task.isOnDate(requestedDate)) {
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
