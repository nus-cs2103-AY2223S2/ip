package duke.command;

import duke.tasklist.TaskList;
import duke.ui.Ui;
/**
 * Represents a "list" command that is entered by the user.
 */
public class ListCommand extends Command {
    private TaskList tasks;


    /**
     * Constructs a ByeCommand.
     */
    public ListCommand(Ui ui, TaskList tasks) {
        super(ui);
        this.tasks = tasks;
    }

    /**
     * Causes the bot to print out the contents of the user's task list.
     */
    @Override
    public void runCommand() {
        tasks.printUserTasks();
    }
}
