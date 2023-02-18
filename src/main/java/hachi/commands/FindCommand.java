package hachi.commands;

import hachi.main.Storage;
import hachi.main.TaskList;
import hachi.main.Ui;
import hachi.tasks.Task;

/**
 * Encapsulates a user instruction to find a task from the to-do list.
 */
public class FindCommand extends Command {
    private String input;
    static String separator = "‿୨♡୧‿‿‿‿୨♡୧‿‿‿‿୨♡୧‿";

    /**
     * FindCommand constructor.
     *
     * @param input The user's input string.
     */
    public FindCommand(String input) {
        this.input = input;
    }

    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            String keyword = this.input.substring(5, input.length());
            int n = 0;
            String msg = "   Here are the matching tasks in your list:";
            for (int i = 0; i < tasks.size(); i++) {
                Task t = tasks.get(i);
                if (t.toString().contains(keyword)) {
                    n++;
                    msg += "   " + n + ". " + t + "\n";
                }
            }
            return separator + "\n" + "\n" + msg;
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}

