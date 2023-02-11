package duke;

/**
 * Command to list all available task given a task list.
 */
public class ListCommand extends Command {
    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String execute(TaskList task, Ui ui, Storage storage) {
        return ui.responseToListCommand(task);
    }
}

