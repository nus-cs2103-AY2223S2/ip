package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;

/**
 * Represents a find command for finding a task by searching for a keyword or keyphrase.
 */
public class FindCommand implements Command {
    /**
     * Updates the task list keyphrase filter to the keyphrase specified in input. Returns a message listing out each
     * task whose description contains the keyword or keyphrase specified in input.
     *
     * @param input {@inheritDoc}
     * @param tasks {@inheritDoc}
     * @return A message listing out each task whose description contains the keyword or keyphrase specified in input.
     * @throws DukeException Indicates that no keyword/keyphrase was specified in input.
     */
    @Override
    public String run(String input, TaskList tasks) throws DukeException {
        String keyphrase = extractValidKeyphrase(input);
        tasks.filter(keyphrase);

        int taskCount = tasks.getSize();
        if (taskCount == 0) {
            return "Leave it to the heedless user to forget the task description.\nNo matching tasks found!";
        } else {
            return String.format("It seems that there are %d matching tasks:\n%s", taskCount, tasks.toString());
        }
    }

    private String extractValidKeyphrase(String input) throws DukeException {
        String[] args = input.split(" ", 2);

        if (args.length != 2 || args[1].trim().isEmpty()) {
            throw new DukeException("The keyphrase to search for cannot be empty!");
        }

        return args[1].trim();
    }
}
