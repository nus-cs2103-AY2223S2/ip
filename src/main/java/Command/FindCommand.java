package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a command from the user to find a task.
 */
public class FindCommand extends Command {

    private String word;

    /**
     * Initialises new instance of FindCommand.
     *
     * @param word The word used to find related tasks.
     */
    public FindCommand(String word) {
        this.word = word;
    }

    /**
     * Checks if command is an exit command.
     *
     * @return false Find task command is not an exit command.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Finds tasks related to the word given by user and prints it in a form of a list.
     *
     * @param tasks A TaskList containing the set of task the user has.
     * @param storage A Storage enabling Duke to store memory.
     * @return String The String message indicating status of action.
     * @throws DukeException
     */
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        TaskList relatedTasks = tasks.findRelated(this.word);
        return Ui.subListTaskResponse(relatedTasks);
    }

}
