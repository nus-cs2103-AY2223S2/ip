package duke.command;

import java.util.Arrays;

import duke.exception.DukeBadInstructionFormatException;
import duke.storage.Storage;
import duke.task.ToDo;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Encapsulates the 'todo' <code>Command</code>> from the user.
 *
 * @author Bo Kuan (LG17)
 * @version CS2103T AY22/23 Semester 2
 */
public class TodoCommand extends Command {
    /**
     * Constructor for an instance of a <code>TodoCommand</code>.
     *
     * @param fullCommand A <code>String</code> of the user's full input.
     */
    public TodoCommand(String fullCommand) {
        super(fullCommand);
    }
    /**
     * Executes the logic behind <code>DeleteCommand</code>, adding a <code>Todo</code>
     * to <code>tasks</code>.
     * @param tasks The <code>TaskList</code> associated with Duke
     * @param ui The <code>Ui</code> associated with Duke
     * @param storage The <code>Storage</code> associated with Duke
     * @throws DukeBadInstructionFormatException If user input is wrong
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage)
            throws DukeBadInstructionFormatException {
        String[] splitted = this.fullCommand.split(" ");
        if (splitted.length == 1) {
            throw new DukeBadInstructionFormatException(
                    "The description of a todo cannot be empty.");
        }

        //Get 'duke.task.ToDo' description
        String[] descriptionArray = Arrays.copyOfRange(splitted, 1, splitted.length);
        String description = String.join(" ", descriptionArray);

        //Make Todo
        ToDo currentTask = new ToDo(description);
        storage.fileAppend(currentTask);
        tasks.append(currentTask);
        ui.showAddedTask(currentTask, tasks);
    }
    /**
     * Returns true if <code>Command</code> is <code>ByeCommand</code>.
     * @return <code>false</code>
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
