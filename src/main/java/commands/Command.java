package commands;

import java.util.ArrayList;

import dukeexceptions.DukeException;
import elems.Storage;
import elems.TaskList;
import elems.Ui;

/**
 * Represents a command to be executed
 * @author clydelhui
 */
public abstract class Command {
    protected ArrayList<String> params;
    private final String keyword;

    /**
     * Creates a command with the given keyword and parameters
     * @param keyword The keyword for the intended command
     * @param params An <code>ArrayList</code> of strings containing the parameters for the command
     */
    public Command(String keyword, ArrayList<String> params) {
        this.keyword = keyword;
        this.params = params;
    }

    /**
     * Executes the <code>Command</code> with the given <code>TaskList</code>,
     *         <code>Ui</code> and <code>Storage</code>
     * @param tasks The <code>TaskList</code> to be acted on by the <code>Command</code>
     * @param ui The <code>Ui</code> to be acted on by the <code>Command</code>
     * @param storage The <code>Storage</code> to be acted on by the <code>Command</code>
     * @throws DukeException when the <code>Command</code> is unable to be executed
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}
