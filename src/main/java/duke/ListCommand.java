package duke;

/**
 * Command to list all available task given a task list.
 */
public class ListCommand extends Command {
    public boolean isExit() {
        return false;
    }

    public void execute(TaskList task, Ui ui, Storage storage){
        ui.responseToListCommand(task);
    }
}

