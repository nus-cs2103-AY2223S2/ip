package duke.commands;
import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Todo;

/**
 * The class for the Todo command which extends Command class.
 */
public class TodoCommand extends Command {
    private String input;

    /**
     * TodoCommand constructor.
     *
     * @param input The user's input.
     */
    public TodoCommand(String input) {
        this.input = input;
    }

    /**
     * @inheritDoc
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            if (5 > input.length()) {
                throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
            }
            Todo td = new Todo(input.substring(5, input.length()));
            tasks.add(td);
            storage.saveTaskList(tasks);
            return ui.confirmationMessage("added", tasks, td);
        } catch (DukeException de) {
            return de.getMessage();
        }
    }
}
