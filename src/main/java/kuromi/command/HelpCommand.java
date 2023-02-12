package kuromi.command;

import kuromi.storage.Storage;
import kuromi.task.TaskList;
import kuromi.view.Ui;

public class HelpCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.show(getReply());
    }

    private String getReply() {
        String msg = "Commands\n";
        msg += "\u2014\u2014\u2014\u2014\u2014\n";
        msg += "Add a task:\n";
        msg += "todo <description>\n";
        msg += "deadline <description> /by <deadline>\n";
        msg += "event <description> /from <start date> /to <end date>\n";
        msg += "\u2014\u2014\u2014\u2014\u2014\n";
        msg += "Delete a task:\n";
        msg += "delete <index>\n";
        msg += "\u2014\u2014\u2014\u2014\u2014\n";
        msg += "List tasks:\n";
        msg += "list\n";
        msg += "\u2014\u2014\u2014\u2014\u2014\n";
        msg += "Mark a task:\n";
        msg += "mark <index>\n";
        msg += "\u2014\u2014\u2014\u2014\u2014\n";
        msg += "Unmark a task:\n";
        msg += "unmark <index>\n";
        msg += "\u2014\u2014\u2014\u2014\u2014\n";
        msg += "Find keyword in tasks:\n";
        msg += "find <keyword>\n";
        msg += "\u2014\u2014\u2014\u2014\u2014\n";
        msg += "Remind upcoming tasks:\n";
        msg += "remind <n>, n = number of tasks\n";
        msg += "remind, n by default is 5\n";
        msg += "\u2014\u2014\u2014\u2014\u2014\n";
        msg += "List my mistakes:\n";
        msg += "mistakes";
        return msg;
    }
}
