package membot.commands;

import membot.model.Task;
import membot.view.Printable;

/**
 * Represents a command which lists out all created <code>Task</code> and their
 * corresponding statuses.
 */
public class ListCommand extends Command {
    /**
     * Generates a <code>ListCommand</code> object.
     *
     * @param ui A Printable object used for UI display.
     */
    protected ListCommand(Printable ui) {
        super(ui);
    }

    /**
     * Prints out all created <code>Task</code> and their corresponding statuses.
     */
    @Override
    public void execute() {
        String[] tasks = Task.listAll();
        if (tasks.length == 0) {
            this.ui.println("Excellent! You do not have any tasks at hand!");
        } else {
            String[] allTasks = Task.listAll();
            String[] a = new String[allTasks.length + 1];
            a[0] = "Here are your updated tasks:";
            for (int i = 1; i <= allTasks.length; ++i) {
                a[i] = String.format("%d. %s", i, allTasks[i - 1]);
            }
            this.ui.println(a);
        }
    }
}
