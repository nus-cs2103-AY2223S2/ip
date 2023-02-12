package duke.command;

import duke.TaskList;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.ui.Ui;

public class UndoCommand extends Command{

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
     * @param list The task list
     * @param ui The ui object
     * @param storage The storage object
     */
    public String execute(TaskList list, Ui ui, Storage storage) {
        if (preCommand instanceof AddTaskCommand) {
            Task deleted = list.delete(list.getLength()-1);
            storage.write(list);
            return ui.delete(deleted, list.getLength());
        } else if(preCommand instanceof DeleteCommand) {
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
            return ui.NoPreCommand();
        } else {
            return ui.InvalidPreCommand();
        }
    }

    /**
     * Checks if this is exit command
     */
    public boolean isExit() {
        return false;
    }

}
