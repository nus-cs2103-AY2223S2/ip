package membot.commands;

import membot.model.Task;
import membot.utils.InputValidator;
import membot.view.Printable;

/**
 * Represents a command which deletes a <code>Task</code>.
 */
public class DeleteCommand extends Command {
    /**
     * Generates a <code>DeleteCommand</code> object.
     *
     * @param input User input into the application.
     * @param ui A Printable object used for UI display.
     */
    protected DeleteCommand(String input, Printable ui) {
        super(input, ui);
    }

    /**
     * Deletes a <code>Task</code>.
     */
    @Override
    public void execute() {
        if (InputValidator.isSingleInputValid(this.input, false, true)) {
            int taskId = Integer.parseInt(input.split(" ")[1]);
            try {
                Task deletedTask = Task.delete(taskId);
                this.ui.println(
                        "Deleted! The task has been deleted:",
                        String.format("%d. %s", taskId, deletedTask.toString())
                );
                this.ui.printSeparator();
                new ListCommand(this.ui).execute();
            } catch (IndexOutOfBoundsException e) {
                this.ui.printlnError("Invalid Task ID!");
            }
        } else {
            this.ui.printlnError(
                    "Invalid Syntax: \"delete [Task ID]\"",
                    "",
                    "Example: \"delete 1\""
            );
        }
    }
}
