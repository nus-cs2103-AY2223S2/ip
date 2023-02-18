package kuromi.command;

import kuromi.storage.Storage;
import kuromi.task.TaskList;
import kuromi.view.Ui;

/**
 * Lists the commands available.
 */
public class HelpCommand extends Command {
    private String line = "\u2014\u2014\u2014\u2014\u2014\n";

    /**
     * Lists the commands that contain
     * @param tasks Task list.
     * @param ui UI of the application to interact with users.
     * @param storage Storage to update when there is an update with the task list.
     * @return
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.show(getReply());
    }

    private String getReply() {
        String msg = "Commands\n" + line;
        msg += "Add a task:\n";
        msg += "todo <description>\n";
        msg += "deadline <description> /by <deadline>\n";
        msg += "event <description> /from <start_date> /to <end_date>\n" + line;
        msg += "Delete a task:\n";
        msg += "delete <index>\n" + line;
        msg += "List tasks:\n";
        msg += "list\n" + line;
        msg += "Mark a task:\n";
        msg += "mark <index>\n" + line;
        msg += "Unmark a task:\n";
        msg += "unmark <index>\n" + line;
        msg += "Find keyword in tasks:\n";
        msg += "find <keyword>\n" + line;
        msg += "Remind upcoming tasks:\n";
        msg += "remind <number_of_tasks>\n";
        msg += "remind\n" + line;
        msg += "List my mistakes:\n";
        msg += "mistakes\n" + line;
        msg += "IMPORTANT: dates must be in yyyy-MM-dd HH:mm format";
        return msg;
    }
}
