package membot.commands;

import membot.model.Task;
import membot.utils.InputValidator;
import membot.view.Printable;

/**
 * Represents a command which marks a <code>Task</code> as to be completed.
 */
public class UndoneCommand extends Command {
    /**
     * Generates a <code>UndoneCommand</code> object.
     *
     * @param input User input into the application.
     * @param ui A Printable object used for UI display.
     */
    protected UndoneCommand(String input, Printable ui) {
        super(input, ui);
    }

    /**
     * Marks a <code>Task</code> as to be completed.
     */
    @Override
    public void execute() {
        if (InputValidator.isSingleInputValid(this.input, false, true)) {
            int taskId = Integer.parseInt(this.input.split(" ")[1]);
            try {
                Task.setStatusNew(taskId);
                this.ui.println(
                        "The task is now marked as not done yet:",
                        String.format("%d. %s", taskId, Task.listOne(taskId))
                );
                this.ui.printSeparator();
                new ListCommand(this.ui).execute();
            } catch (IndexOutOfBoundsException e) {
                this.ui.printlnError("Invalid Task ID!");
            }
        } else {
            this.ui.printlnError(
                    "Invalid Syntax: \"undone [Task ID]\"",
                    "",
                    "Example: \"undone 3\""
            );
        }
    }
}
