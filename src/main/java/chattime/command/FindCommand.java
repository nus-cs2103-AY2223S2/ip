package chattime.command;

import chattime.TaskList;
import chattime.storage.Storage;
import chattime.task.Task;
import chattime.ui.Ui;

import java.time.format.DateTimeFormatter;

public class FindCommand extends Command {

    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(Ui ui, TaskList taskList, Storage storage) {
        if (taskList.getList().size() == 0) {
            ui.emptyList();
        } else {
            int i = 1, total = 0, pending = 0;
            String message = "I've matched the task(s) with your search keyword for you:";

            for (Task task : taskList.getList()) {
                if (task.matchDescription(keyword)) {
                    message = message.concat(String.format("\n     %d. %s", i, task));
                    i++;
                    total++;
                    if (!task.getTaskStatus()) {
                        pending++;
                    }
                }
            }

            message += "\n     Here have " + total + " result(s) with keyword " + keyword +
                    ". " + pending + " task(s) to go.";

            ui.replyUser(message);
        }
    }
}
