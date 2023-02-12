package duke.command;

import duke.exceptions.DukeException;
import duke.task.TaskList;

/**
 * A command to search for tasks.
 */
public class FindCommand {
    /**
     * Finds all tasks associated with a given word from a list of tasks.
     * @param tasks the list of tasks.
     * @param input user's input containing the word to look for.
     * @return a string of tasks associated to the specified word.
     * @throws DukeException if no word were supplied.
     */
    public static String executeFind(TaskList tasks, String input) throws DukeException {
        if (input.length() < 6) {
            throw new DukeException("Please supply a word to find!");
        }
        String toFind = input.substring(5);
        return tasks.find(toFind);
    }
}
