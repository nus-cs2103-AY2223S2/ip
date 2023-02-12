package duke.command;

import duke.TaskList;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.ui.Ui;


public class MarkUndoneCommand extends Command {

    protected int index;

    /**
     * Initialises the object
     *
     * @param index Index of the task in the list
     */
    public MarkUndoneCommand(int index) {
        assert index >= 0;
        this.index = index;
    }


    /**
     * Executes the current command
     *
     * @param tasks The task list
     * @param ui The ui object
     * @param storage The storage object
     */
    public String execute(TaskList tasks, Ui ui, Storage storage)throws
            IllegalArgumentException{
        Task marked = tasks.unmark(index);
        assert marked != null: "Task marked should exist";
        storage.write(tasks);
        return ui.unmark(marked);
    }

    /**
     * Checks if this is exit command
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Gets the index of the marked task
     * @return The index of the marked task
     */
    public int getIndex() {
        return this.index;
    }

}
