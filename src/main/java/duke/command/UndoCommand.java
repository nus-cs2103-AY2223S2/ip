package duke.command;

import duke.TaskList;
import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.ui.Ui;

public class UndoCommand extends Command {

    protected Command preCommand;

    /**
     * Initialises the object
     *
     * @param preCommand The previousCommand
     */
    public UndoCommand(Command preCommand) {
        this.preCommand = preCommand;
    }

    /**
     * Executes the current command
     *
     * @param list    The task list
     * @param ui      The ui object
     * @param storage The storage object
     * @throws DukeException Throws DukeException of a specific massage
     */
    public String execute(TaskList list, Ui ui, Storage storage) throws DukeException {
        if (preCommand instanceof AddTaskCommand) {
            Task deleted = list.delete(list.getLength() - 1);
            storage.write(list);
            return Ui.delete(deleted, list.getLength());
        } else if (preCommand instanceof DeleteCommand) {
            list.add((((DeleteCommand) preCommand).getDeleted()));
            storage.write(list);
            return ui.addTask(((DeleteCommand) preCommand).getDeleted(), list.getLength());
        } else if (preCommand instanceof MarkDoneCommand) {
            Task unmarked = list.unmark(((MarkDoneCommand) preCommand).getIndex());
            storage.write(list);
            return ui.unmark(unmarked);
        } else if (preCommand instanceof MarkUndoneCommand) {
            Task marked = list.mark(((MarkUndoneCommand) preCommand).getIndex());
            storage.write(list);
            return ui.mark(marked);
        } else if (preCommand == null) {
            return Ui.cannotFindPreCommand();
        } else {
            return Ui.failUndo();
        }
    }

    /**
     * Checks if this is exit command
     */
    public boolean isExit() {
        return false;
    }

}
