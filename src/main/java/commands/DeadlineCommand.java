package commands;

import model.Deadline;
import utils.InputValidator;
import utils.InvalidCommandException;
import utils.NoDeadlineFoundException;
import view.Printable;

public class DeadlineCommand extends Command {
    public DeadlineCommand(String input, Printable ui) {
        super(input, ui);
    }

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
