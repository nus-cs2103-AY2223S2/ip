package commands;

import java.io.IOException;
import java.util.ArrayList;

import dukeexceptions.DukeException;
import dukeexceptions.IllegalCommandException;
import dukeexceptions.IllegalInputException;
import dukeexceptions.TaskListIndexOutOfBoundsException;
import elems.Storage;
import elems.TaskList;
import elems.Ui;
import items.Task;

/**
 * Represents a <code>Command</code> that deletes a task from the given <code>TaskList</code>
 * @author clydelhui
 */
public class DeleteCommand extends Command {

    /**
     * Constructor that creates a <code>DeleteCommand</code> object given a keyword and
     *      an <code>ArrayList</code> of strings of parameters
     * @param keyword The keyword for the <code>DeleteCommand</code>
     * @param params An <code>ArrayList</code> containing the parameters of the command
     *               in <code>String</code> type
     * @throws IllegalCommandException If the given keyword does not match any valid keywords
     */
    public DeleteCommand(String keyword, ArrayList<String> params) throws IllegalCommandException {
        super(keyword, params);
        if (!"delete".equals(keyword)) {
            throw new IllegalCommandException("Invalid DeleteCommand");
        }
    }

    /**
     * {@inheritDoc}
     * @param tasks The <code>TaskList</code> to be acted on by the <code>Command</code>
     * @param ui The <code>Ui</code> to be acted on by the <code>Command</code>
     * @param storage The <code>Storage</code> to be acted on by the <code>Command</code>
     * @throws DukeException when the <code>Task</code> cannot be successfully deleted from the <code>TaskList</code>
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (this.params.size() != 1) {
            throw new IllegalInputException("Wrong number of arguments for this command!");
        }
        try {
            int deleteIndex = Integer.parseInt(this.params.get(0));
            Task deleted = tasks.delete(deleteIndex);
            storage.refreshStorage(tasks);
            ui.display("Successfully deleted the following task! \n"
                    + deleted.toString());
        } catch (NumberFormatException e) {
            throw new IllegalInputException("Invalid task index chosed!");
        } catch (TaskListIndexOutOfBoundsException e) {
            ui.errorDisplay(e);
        } catch (IOException e) {
            ui.display("Seems like there is something wrong with the storage file \n"
                    + "Any updates will not be saved!");
            ui.errorDisplay(e);
            e.printStackTrace();

        }
    }
}
