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
     * @param ui the user interface to interact with the user
     * @param storage used to save the TaskList to be retrieved in the future
     * @throws DukeException if the String index is not an integer OR if index is not in range of size of TaskList
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        TaskList tasksWithWords = tasks.find(this.command);
        if (tasksWithWords.isEmpty()) {
            throw new DukeException("There are no tasks found with words:\n" + this.command);
        }

        String str = "List:";
        for (int i = 1; i <= tasksWithWords.size(); i++) {
            str += String.format("\n\t%d. %s", i, tasksWithWords.get(i));
        }
        ui.print(str);
    }
}
