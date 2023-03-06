package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * Represents the command used to find tasks containing words or phrases
 */
public class FindCommand extends Command {
    /**
     * Returns a FindCommand
     *
     * @param wordsToFind String for word(s) to store to search in TaskList
     */
    public FindCommand(String wordsToFind) {
        super(wordsToFind);
    }
    /**
     * Finds tasks in TaskList which contains the words stored
     * Display the output via Ui showing the tasks found with the words
     * Saves the file via Storage
     *
     * @param tasks TaskList of all the tasks
     * @param ui Ui the user interface to interact with the user
     * @param storage Storage used to save the TaskList to be retrieved in the future
     * @throws DukeException if the String index is not an integer OR if index is not in range of size of TaskList
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        TaskList tasksWithWords = tasks.find(this.command);
        if (tasksWithWords.isEmpty()) {
            throw new DukeException(noItemsFoundMessage());
        }

        ui.print(listString(tasksWithWords));
    }
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        TaskList tasksWithWords = tasks.find(this.command);
        if (tasksWithWords.isEmpty()) {
            throw new DukeException(noItemsFoundMessage());
        }

        return listString(tasksWithWords);
    }

    private String noItemsFoundMessage() {
        return String.format("There are no tasks found with words:\n%s", this.command);
    }
}
