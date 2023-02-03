package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a find command for finding a task by searching for a keyword or keyphrase.
 */
public class FindCommand implements Command {
    /**
     * Returns a message listing out each task whose description contains the keyword or keyphrase specified in input.
     *
     * @param input {@inheritDoc}
     * @param tasks {@inheritDoc}
     * @return A message listing out each task whose description contains the keyword or keyphrase specified in input.
     * @throws DukeException Indicates that no keyword/keyphrase was specified in input.
     */
    @Override
    public String run(String input, TaskList tasks) throws DukeException {
        List<Integer> matchedTaskIndexes = getMatchedTaskIndexes(input, tasks);
        return getMessage(tasks, matchedTaskIndexes);
    }

    private List<Integer> getMatchedTaskIndexes(String input, TaskList tasks) throws DukeException {
        String keyphrase = extractValidKeyphrase(input);
        return filterTasksByKeyphrase(tasks, keyphrase);
    }

    private String extractValidKeyphrase(String input) throws DukeException {
        String[] args = extractArgs(input);

        validateNonEmptyKeyphrase(args);

        return args[1].trim();
    }

    private String[] extractArgs(String input) {
        return input.split(" ", 2);
    }

    private void validateNonEmptyKeyphrase(String[] args) throws DukeException {
        if (args.length != 2 || args[1].trim().isEmpty()) {
            throw new DukeException("The keyphrase to search for cannot be empty!");
        }
    }

    private List<Integer> filterTasksByKeyphrase(TaskList tasks, String keyphrase) {
        List<Integer> taskIndexes = new ArrayList<Integer>();
        for (int i = 0; i < tasks.size(); ++i) {
            if (tasks.get(i).getDescription().contains(keyphrase)) {
                taskIndexes.add(i);
            }
        }

        return taskIndexes;
    }

    private String getMessage(TaskList tasks, List<Integer> matchedTaskIndexes) {
        if (matchedTaskIndexes.isEmpty()) {
            return "The task you're searching for DOESN'T EXIST!";
        } else {
            String matchedTaskListStr = getMatchedTaskListStr(tasks, matchedTaskIndexes);
            return String.format("It seems that there are %d matching tasks:\n%s", matchedTaskIndexes.size(),
                    matchedTaskListStr);
        }
    }

    private String getMatchedTaskListStr(TaskList tasks, List<Integer> matchedTasks) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Integer index : matchedTasks) {
            stringBuilder.append(String.format("%d.%s\n", index + 1, tasks.get(index).toString()));
        }

        return stringBuilder.toString().trim();
    }
}
