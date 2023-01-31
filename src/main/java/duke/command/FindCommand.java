package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * A subclass of Command that represents the
 * command to look for task that contains
 * certain words in its description.
 *
 * @author Oskar Lew
 * @version 0.1
 * @since 0.1
 */
public class FindCommand extends Command {
    protected String word;

    /**
     * Constructor of FindCommand.
     * @param command Command from the user.
     */
    public FindCommand(String[] command) {
        super(command);
    }

    /**
     * Method to print to console a list of tasks
     * with the descriptions containing the keyword.
     * @param tasks List of tasks.
     * @param ui Ui of chat.
     * @param storage Storage of Duke.
     * @return List of task containing the keyword.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            if (this.command.length == 1 || this.command.length > 2) {
                throw new DukeException(null, null);
            }
            this.word = command[1];
            TaskList temp = new TaskList();
            for (int i = 0; i < tasks.size(); i++) {
                Task currentTask = tasks.get(i);
                if (currentTask.hasWord(this.word)) {
                    temp.add(currentTask);
                }
            }
            if (temp.size() == 0) {
                return ui.noMatchingWords(word);
            }
            return ui.showListWithMatchedWords(temp);
        } catch (DukeException e) {
            return ui.findError();
        }
    }
}
