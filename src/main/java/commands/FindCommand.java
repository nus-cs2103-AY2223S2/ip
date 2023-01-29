package commands;

import java.util.ArrayList;

import model.Task;
import utils.InputValidator;
import view.Printable;
import view.Printer;

/**
 * Represents a command which finds a <code>Task</code> that contains a certain keyword
 * in its title.
 */
public class FindCommand extends Command {
    public FindCommand(String input, Printable ui) {
        super(input, ui);
    }

    @Override
    public void execute() {
        if (InputValidator.isFindInputValid(this.input)) {
            String keyword = input.substring(5);

            ArrayList<Task> tasks = Task.find(keyword);
            if (tasks.isEmpty()) {
                this.ui.printlnIndent("There are no tasks containing \"" + keyword + "\":");
                return;
            }

            this.ui.printIndent("Here are the tasks containing \"" + keyword + "\":");

            String[] sTasks = new String[tasks.size()];
            for (int i = 0; i < tasks.size(); ++i) {
                sTasks[i] = tasks.get(i).toString();
            }
            Printer.listPrint(sTasks);
        } else {
            this.ui.printlnError("Invalid Syntax - \"find [keyword]\" (e.g. \"find study\")");
        }
    }
}
