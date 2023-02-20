package commands;

import java.io.IOException;
import java.util.ArrayList;

import dukeexceptions.DukeException;
import dukeexceptions.EmptyTaskListException;
import dukeexceptions.IllegalCommandException;
import dukeexceptions.IllegalInputException;
import elems.Storage;
import elems.TaskList;
import elems.Ui;

/**
 * Represents a Command that modifies a <code>TaskList</code> without changing any of the <code>Tasks</code>
 * within the <code>TaskList</code>
 * @author clydelhui
 */
public class ListModifyCommand extends Command {

    /**
     * Creates a new ListModifyCommand
     * @param keyword The keyword for the command
     * @param params The parameters for the command
     * @throws IllegalCommandException when the command is not recognised as valid
     */
    public ListModifyCommand(String keyword, ArrayList<String> params) throws IllegalCommandException {
        super(keyword, params);
        if (!"sort".equals(keyword)) {
            throw new IllegalCommandException("Invalid ListModifyCommand");
        }
    }

    /**
     * Executes the <code>ListModifyCommand</code> with the given <code>TaskList</code>,
     * <code>Ui</code> and <code>Storage</code>
     *
     * @param tasks   The <code>TaskList</code> to be acted on by the <code>Command</code>
     * @param ui      The <code>Ui</code> to be acted on by the <code>Command</code>
     * @param storage The <code>Storage</code> to be acted on by the <code>Command</code>
     * @throws dukeexceptions.DukeException when the <code>Command</code> is unable to be executed
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (this.params.size() != 1 && !this.params.get(0).equals("")) {
            throw new IllegalInputException("Wrong number of arguments!");
        }
        if (tasks.getSize() == 0) {
            throw new EmptyTaskListException("Trying to sort an empty task list");
        }
        try {
            tasks.sortByDescription();
            storage.refreshStorage(tasks);
            ui.dukeDisplay("I have sorted the list by description!\nTry using the \"list\""
                    + "command to see the new ordering of the list");
        } catch (IOException e) {
            ui.dukeDisplay("Seems like there is something wrong with the storage file \n"
                    + "Any updates will not be saved!");
            ui.errorDisplay(e);
            e.printStackTrace();
        }
    }
}
