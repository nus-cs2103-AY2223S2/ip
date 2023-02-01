package membot.commands;

import java.util.ArrayList;

import membot.model.Task;
import membot.utils.InputValidator;
import membot.view.Printable;

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
            this.ui.listPrint(sTasks);
        } else {
            this.ui.printlnError("Invalid Syntax - \"find [keyword]\" (e.g. \"find study\")");
        }
    }
}
