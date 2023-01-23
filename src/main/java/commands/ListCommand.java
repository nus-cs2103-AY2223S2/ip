package commands;

import model.Task;
import view.Printer;
import view.Printable;

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
    public ListCommand(Printable ui) {
        super(ui);
    }

    /**
     * Prints out all created <code>Task</code> and their corresponding statuses.
     */
    @Override
    public void execute() {
        String[] tasks = Task.listAll();
        if (tasks.length == 0) {
            this.ui.printlnIndent("Excellent! You do not have any tasks at hand!");
        } else {
            this.ui.printIndent("Here are your updated tasks:");
            Printer.listPrint(Task.listAll());
        }
    }
}
