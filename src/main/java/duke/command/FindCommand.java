package duke.command;

import static duke.command.CommandValidations.validateNotEmptyArgs;

import duke.DukeException;
import duke.task.Task;
import duke.task.TaskList;

/**
 * A command that searches for any tasks that contains a keyword.
 * @author Junyi
 */
public class FindCommand extends Command {

    private final TaskList taskList;
    private final String[] keywords;

    /**
     * Constructor for FindCommand.
     * @param taskList TaskList of Duke's tasks.
     * @param keywords The keyword to identify tasks to filter by.
     */
    public FindCommand(TaskList taskList, String... keywords) {
        this.taskList = taskList;
        this.keywords = keywords;
    }

    /**
     * Factory method to create find command from user input string
     * @param inputString The mentioned input string from user.
     * @param taskList TaskList of Duke's tasks.
     * @return An instance of FindCommand.
     */
    public static FindCommand createFindCommand(String inputString, TaskList taskList) throws DukeException {
        validateNotEmptyArgs(inputString);
        String keywords = inputString.substring(5);

        return new FindCommand(taskList, keywords.split(" "));
    }

    @Override
    public String execute() throws DukeException {
        StringBuilder stringBuilder = new StringBuilder();

        if (keywords.length == 0) {
            return "No keywords given!";
        }

        StringBuilder joinedKeywords = new StringBuilder(keywords[0]);
        for (int i = 1; i < keywords.length; i++) {
            joinedKeywords.append(", ").append(keywords[i]);
        }

        stringBuilder.append(String.format("Found tasks with keywords: %s\n", joinedKeywords));
        for (Task task : taskList.allTasks()) {
            for (String keyword : keywords) {
                if (task.containsKeyword(keyword)) {
                    stringBuilder.append(task);
                    stringBuilder.append("\n");
                }
            }
        }

        return stringBuilder.toString();
    }
}
