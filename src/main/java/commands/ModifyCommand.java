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

/**
 * Represents a command which modifies a <code>Task</code> in a given <code>TaskList</code>
 * @author clydelhui
 */
public class ModifyCommand extends Command {
    enum ModifyType {
        MARK,
        UNMARK
    }

    private final ModifyType modifyType;

    /**
     * Constructor that creates an <code>AddCommand</code> object given a keyword and
     * an <code>ArrayList</code> of strings of parameters
     * @param keyword The keyword for the <code>AddCommand</code>
     * @param params An <code>ArrayList</code> containing the parameters of the command
     *               in <code>String</code> type
     * @throws IllegalCommandException If the given keyword does not match any valid keywords
     */
    public ModifyCommand(String keyword, ArrayList<String> params) throws IllegalCommandException {
        super(keyword, params);

        switch (keyword) {
        case "mark":
            this.modifyType = ModifyType.MARK;
            break;
        case "unmark":
            this.modifyType = ModifyType.UNMARK;
            break;
        default:
            throw new IllegalCommandException("Invalid keyword for ModifyCommand");
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (this.params.size() != 1) {
            throw new IllegalInputException("Wrong number of parameters for this command!");
        }
        try {
            int taskIndex = Integer.parseInt(this.params.get(0));
            switch (this.modifyType) {
            case MARK:
                tasks.markTask(taskIndex);
                break;
            case UNMARK:
                tasks.unmarkTask(taskIndex);
                break;
            default:
                throw new IllegalCommandException("Invalid type of ModifyCommand");
            }
            storage.refreshStorage(tasks);
            ui.display("Task successfully modified! \n" + tasks.getTaskString(taskIndex));
        } catch (NumberFormatException e) {
            throw new IllegalInputException("Invalid task item selected!");
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
