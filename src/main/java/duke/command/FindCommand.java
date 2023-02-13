package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Represents a find using keyword command.
 */
public class FindCommand extends Command {

    private String[] keywords;

    /**
     * Constructs a find using keyword command.
     *
     * @param toFind String keyword to matched with.
     */
    public FindCommand(String ... toFind) {
        this.keywords = toFind;
    }

    @Override
    public boolean isGoodbye() {
        return false;
    }

    /**
     * Lists all tasks that give a match with keyword.
     * Returns String representation of tasks that matched keyword.
     *
     * @param tasks Tasklist object.
     * @param storage Storage object.
     * @param ui Ui Object.
     * @return String representation of matching tasks.
     */
    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) {
        TaskList matchTasks = tasks.getMatchingTasks(this.keywords);
        if (matchTasks.getNumTasks() == 0) {
            return ui.noMatchingTask();
        } else {
            return ui.printTasks(matchTasks);
        }
    }
}
