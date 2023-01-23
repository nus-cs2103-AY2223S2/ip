package commands;

import model.Deadline;
import utils.InputValidator;
import utils.InvalidCommandException;
import utils.NoDeadlineFoundException;
import view.Printable;

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
    public DeadlineCommand(String input, Printable ui) {
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
            this.ui.printIndent(task.toString());

            this.ui.printIndent("");
            new ListCommand(this.ui).execute();
        } catch (IndexOutOfBoundsException | NoDeadlineFoundException | InvalidCommandException e) {
            this.ui.printlnError("Invalid Syntax - \"deadline [title] /by [deadline]\"" +
                    "(e.g. \"deadline physics project /by tomorrow 3pm\")");
        }
    }
}
