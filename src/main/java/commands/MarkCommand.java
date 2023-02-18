package commands;

import storage.Storage;
import storage.TaskList;
import tasks.Task;
import ui.Ui;

/** This class is to mark or unmark a task as complete */
public class MarkCommand extends Command {
    /** Boolean value indicating whether task is to be marked or unmarked */
    private boolean toMark;
    /** Index of task to mark or unmark */
    private int index;

    /**
     * Creates command to mark or unmark task of specified index
     *
     * @param toMark parsed from user input "mark" or "unmark"
     * @param i user input of index to mark or unmark
     */
    public MarkCommand(boolean toMark, int i) {
        this.toMark = toMark;
        this.index = i - 1;
    }

    /**
     * Marks or unmarks task in tasklist
     *
     * @param taskList the list that contains the task
     * @param ui {@inheritDoc}
     * @param storage {@inheritDoc}
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Task t = taskList.get(index);
        ui.printResponse(toMark ? t.mark() : t.unmark());
    }
}
