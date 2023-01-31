package duke;

/**
 * Command to list all available task given a task list.
 */
public class ListCommand extends Command {
    public boolean isExit() {
        return false;
    }

    public String execute(TaskList task, Ui ui, Storage storage) {
        return ui.responseToListCommand(task);
    }
}

