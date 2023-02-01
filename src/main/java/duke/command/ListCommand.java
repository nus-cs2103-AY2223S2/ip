package duke.command;

import duke.Ui;
import duke.TaskList;
import duke.Storage;
public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";
    public ListCommand() {
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.listTasks();
    }
}
