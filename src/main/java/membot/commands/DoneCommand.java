package membot.commands;

import membot.model.Task;
import membot.utils.InputValidator;
import membot.view.Printable;

/**
 * Represents a command which marks a <code>Task</code> as completed.
 */
public class DoneCommand extends Command {
    /**
     * Generates a <code>DoneCommand</code> object.
     *
     * @param input User input into the application.
     * @param ui A Printable object used for UI display.
     */
    protected DoneCommand(String input, Printable ui) {
        super(input, ui);
    }

    /**
     * Marks a <code>Task</code> as completed.
     */
    @Override
    public void execute() {
        if (InputValidator.isSingleInputValid(this.input, false, true)) {
            int taskId = Integer.parseInt(this.input.split(" ")[1]);
            try {
                Task.setStatusCompleted(taskId);
                this.ui.println(
                        "Well done! The task is marked as completed:",
                        String.format("%d. %s", taskId, Task.listOne(taskId)),
                        ""
                );
                this.ui.printSeparator();
                new ListCommand(this.ui).execute();
            } catch (IndexOutOfBoundsException e) {
                this.ui.printlnError("Invalid Task ID!");
            }
        } else {
            this.ui.printlnError(
                    "Invalid Syntax: \"done [Task ID]\"",
                    "",
                    "Example: \"done 4\""
            );
        }
    }
}
