package duke.command;

import duke.exceptions.DukeException;
import duke.task.TaskList;

public class FindCommand {
    public static String executeFind(TaskList tasks, String input) throws DukeException {
        if (input.length() < 6) {
            throw new DukeException("Please supply a word to find!");
        }
        String toFind = input.substring(5);
        return tasks.find(toFind);
    }
}
