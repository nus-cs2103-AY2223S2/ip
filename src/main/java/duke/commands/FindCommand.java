package duke.commands;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;

/**
 * The class for the Find command which extends Command class.
 */
public class FindCommand extends Command {
    private String input;

    /**
     * FindCommand constructor.
     *
     * @param input The user's input.
     */
    public FindCommand(String input) {
        this.input = input;
    }

    /**
     * @inheritDoc
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            String keyword = this.input.substring(5, input.length());
            int counter = 0;
            String result = "Here are the matching tasks in your list:\n";
            for (int i = 0; i < tasks.size(); i++) {
                Task t = tasks.get(i);
                if (t.contains(keyword)) {
                    counter++;
                    result += "" + counter + ". " + t + "\n";
                } else if (t instanceof Deadline) {
                    if (((Deadline) t).dateContains(keyword)) {
                        counter++;
                        result += "" + counter + ". " + t + "\n";
                    }
                } else if (t instanceof Event) {
                    if (((Event) t).dateContains(keyword)) {
                        counter++;
                        result += "" + counter + ". " + t + "\n";
                    }
                }
            }
            assert counter > 0 : "There are no tasks found.";
            return result;
        } catch (AssertionError ae) {
            return ae.getMessage();
        }
    }
}
