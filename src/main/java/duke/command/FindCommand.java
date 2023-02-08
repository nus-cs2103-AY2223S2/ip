package duke.command;

import duke.Duke;
import duke.task.Task;

/**
 * Handles a request to find task which description contains specified search words.
 */
public class FindCommand extends Command {

    /** Keyword of this command */
    static final String CMD_KEYWORD = "find";

    private final String searchWords;

    /**
     * Constructs an instance of FindCommand.
     *
     * @param userInput String containing the whole input provided by the user.
     */
    public FindCommand(String userInput) {
        searchWords = userInput.replaceFirst(CMD_KEYWORD, "").trim();
    }

    /**
     * @inheritDoc
     */
    @Override
    public ReturnCode execute(Duke duke) {

        StringBuilder sb = new StringBuilder();

        // Find for task containing search words
        for (int i = 0; i < duke.taskList.size(); i++) {
            Task task = duke.taskList.get(i);
            if (task.getDescription().contains(searchWords)) {
                sb.append("\t").append(i + 1).append(". ").append(task).append("\n");
            }
        }

        // Display results
        if (sb.length() == 0) {
            duke.ui.println("Sorry, no match found.");
            return ReturnCode.FAILURE;

        } else if (sb.length() > 0) {
            duke.ui.println("Here are the matching tasks in your list:");
            duke.ui.println(sb.toString());
            return ReturnCode.SUCCESS;

        } else {
            assert false : "(How can StringBuilder have negative length?!)";
            return ReturnCode.FATAL;
        }
    }
}
