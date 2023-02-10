package duke.commands;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Task;
import duke.tasks.Todo;

/**
 * The class for the Update command which extends Command class.
 */
public class UpdateCommand extends Command {
    private String input;

    /**
     * TodoCommand constructor.
     *
     * @param input The user's input.
     */
    public UpdateCommand(String input) {
        this.input = input;
    }

    /**
     * @inheritDoc
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            String[] words = this.input.split(" ");
            if (words.length <= 1) {
                throw new DukeException(ui.emptyDescriptionError());
            }
            int index = Integer.parseInt(words[1]);
            Task t = tasks.get(index-1);
            t.update(this.input.split("/description")[1]);
            storage.saveTaskList(tasks);
            return ui.confirmationMessage("updated", tasks, t);
        } catch (DukeException de) {
            return de.getMessage();
        } catch (NumberFormatException nfe) {
            return ui.taskNumberNotSpecified();
        }
    }
}
