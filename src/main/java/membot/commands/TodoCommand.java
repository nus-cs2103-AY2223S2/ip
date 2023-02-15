package membot.commands;

import membot.model.ToDo;
import membot.utils.InputValidator;
import membot.utils.InvalidCommandException;
import membot.view.Printable;

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
    protected TodoCommand(String input, Printable ui) {
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
            this.ui.println("New task created!", "", task.toString());
            this.ui.printSeparator();
            new ListCommand(this.ui).execute();
        } catch (InvalidCommandException e) {
            this.ui.printlnError(
                    "Invalid Syntax: \"todo [title]\"",
                    "",
                    "Example: \"todo math homework\""
            );
        }
    }
}
