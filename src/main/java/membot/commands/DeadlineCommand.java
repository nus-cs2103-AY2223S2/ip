package membot.commands;

import membot.model.Deadline;
import membot.utils.InputValidator;
import membot.utils.InvalidCommandException;
import membot.utils.NoDeadlineFoundException;
import membot.view.Printable;

/**
 * Represents a command which creates a <code>Task</code> that has a deadline attached to it.
 */
public class DeadlineCommand extends Command {
    /**
     * Generates a <code>DeadlineCommand</code> object.
     *
     * @param input User input into the application.
     * @param ui A Printable object used for UI display.
     */
    protected DeadlineCommand(String input, Printable ui) {
        super(input, ui);
    }

    /**
     * Creates a <code>Deadline</code> task.
     */
    @Override
    public void execute() {
        try {
            String[] normalised = InputValidator.normaliseDeadlineInput(input);
            Deadline task = new Deadline(normalised[1], normalised[2]);
            this.ui.println(task.toString());
            this.ui.printSeparator();
            new ListCommand(this.ui).execute();
        } catch (IndexOutOfBoundsException | NoDeadlineFoundException | InvalidCommandException e) {
            this.ui.printlnError(
                    "Invalid Syntax: \"deadline [title] /by [deadline]\"",
                    "",
                    "Example: \"deadline physics project /by tomorrow 3pm\""
            );
        }
    }
}
