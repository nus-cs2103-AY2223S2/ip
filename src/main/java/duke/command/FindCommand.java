package duke.command;

import duke.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.Ui;

public class FindCommand extends Command {
    String keyword;
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList filtered = tasks.find(this.keyword);
        String msg = "Here are the matching tasks in your list:\n";
        msg += filtered.toString();
        ui.show(msg);
    }
}
