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
    protected FindCommand(String input, Printable ui) {
        super(input, ui);
    }

    @Override
    public void execute() {
        if (InputValidator.isSingleInputValid(this.input, true, false)) {
            String keyword = input.substring(5);

            ArrayList<Task> tasks = Task.find(keyword);
            if (tasks.isEmpty()) {
                this.ui.println("There are no tasks containing \"" + keyword + "\":");
                this.ui.printSeparator();
                return;
            }

            String[] a = new String[tasks.size() + 1];
            a[0] = String.format("Here are the tasks containing \"%s\":", keyword);
            for (int i = 1; i <= tasks.size(); ++i) {
                a[i] = String.format("%d. %s", i, tasks.get(i - 1).toString());
            }

            this.ui.println(a);
        } else {
            this.ui.printlnError(
                    "Invalid Syntax: \"find [keyword]\"",
                    "",
                    "Example: \"find study\""
            );
        }
    }
}
