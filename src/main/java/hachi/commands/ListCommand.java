package hachi.commands;

import hachi.main.TaskList;
import hachi.main.Ui;
import hachi.main.Storage;

/**
 * Encapsulates a user instruction to display the to-do list.
 */
public class ListCommand extends Command {
    private String input;
    static String separator = "‿୨♡୧‿‿‿‿୨♡୧‿‿‿‿୨♡୧‿";

    /**
     * ListCommand constructor.
     *
     * @param input The user's input string.
     */
    public ListCommand(String input) {
        this.input = input;
    }

    public String execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.size() > 0) {
            String msg = separator + "\n" + "\n" + "   Here are the tasks in your list:\n";
            for (int i = 0; i < tasks.size(); i++) {
                int num = i + 1;
                msg += "    " + num + ". " + tasks.get(i) + "\n";
            }
            return msg;
        } else {
            return ui.noTasksMessage();
        }
    }
}
