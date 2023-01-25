package chattime.command;

import chattime.storage.Storage;
import chattime.task.Task;
import chattime.TaskList;
import chattime.ui.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ListCommand extends Command {

    private LocalDate requestDate;

    public ListCommand(LocalDate date) {
        requestDate = date;
    }

    @Override
    public void execute(Ui ui, TaskList taskList, Storage storage) {
        if (requestDate == null) {
            displayList(ui, taskList);
        } else {
            listTime(ui, taskList);
        }
    };

    public void displayList(Ui ui, TaskList taskList) {
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

    public void listTime(Ui ui, TaskList taskList) {
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
