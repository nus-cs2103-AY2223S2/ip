package duke.commands;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * The class for the List command which extends Command class.
 */
public class ListCommand extends Command {
    private String input;

    /**
     * ListCommand constructor.
     *
     * @param input The user's input.
     */
    public ListCommand(String input) {
        this.input = input;
    }

    /**
     * @inheritDoc
     */
    public String execute(TaskList tasks, Storage storage) {
        if (tasks.size() <= 0) {
            return Ui.noTasksMessage();
        }
        String result = "Here are the tasks in your list:\n";
        for (int i = 0; i < tasks.size(); i++) {
            int num = i + 1;
            result += "    "
                    + num
                    + ". "
                    + tasks.get(i)
                    + "\n";
        }
        return result;
    }
}
