package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Represents a find using keyword command.
 */
public class FindCommand extends Command {

    private String keyword;

    /**
     * Constructs a find using keyword command.
     *
     * @param toFind String keyword to matched with.
     */
    public FindCommand(String toFind) {
        this.keyword = toFind;
    }

    @Override
    public boolean isGoodbye() {
        return false;
    }

    /**
     * Lists all tasks that give a match with keyword.
     *
     * @param tasks Tasklist object.
     * @param storage Storage object.
     * @param ui Ui Object.
     */
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        TaskList matchTasks = tasks.getMatchingTasks(this.keyword);
        if (matchTasks.getNumTasks() == 0) {
            ui.noMatchingTask();
        } else {
            ui.printTasks(matchTasks);
        }
    }
}
