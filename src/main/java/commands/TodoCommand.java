package commands;

import model.ToDo;
import utils.InputValidator;
import utils.InvalidCommandException;
import view.Printable;

public class TodoCommand extends Command {
    public TodoCommand(String input, Printable ui) {
        super(input, ui);
    }

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
