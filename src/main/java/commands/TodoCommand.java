package commands;

import model.ToDo;
import utils.InputValidator;
import utils.InvalidCommandException;
import view.Printable;

/**
 * Represents a command which creates a basic <code>Task</code>.
 */
public class TodoCommand extends Command {
    /**
     * Generates a <code>TodoCommand</code> object.
     *
     * @param input User input into the application.
     * @param ui A Printable object used for UI display.
     */
    public TodoCommand(String input, Printable ui) {
        super(input, ui);
    }

    /**
     * Creates a <code>Todo</code> task.
     */
    @Override
    public void execute() {
        try {
            String[] normalised = InputValidator.normaliseTodoInput(this.input);
            ToDo task = new ToDo(normalised[1]);
            this.ui.printIndent(task.toString());

            this.ui.printIndent("");
            new ListCommand(this.ui).execute();
        } catch (InvalidCommandException e) {
            this.ui.printlnError("Invalid Syntax - \"todo [title]\" (e.g. \"todo math homework\")");
        }
    }
}
