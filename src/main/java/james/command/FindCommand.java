package james.command;

import james.exception.JamesException;
import james.jamesbot.Storage;
import james.jamesbot.Ui;
import james.task.TaskList;

/**
 * Finds tasks based on keyword.
 */
public class FindCommand extends Command {
    public static final String COMMAND = "find";

    public static final String MESSAGE = COMMAND + ": Finds tasks containing the keywords.\n"
            + "(e.g find book)";

    public static final String MESSAGE_FORMAT = "to find a task containing keywords, "
            + "use the following format:\n'find [keywords]'\nhere is an example, 'find book'";

    private String userInput;
    private TaskList matchedTasks;

    /**
     * Constructs a FindCommand object.
     *
     * @param userInput The user input.
     */
    public FindCommand(String userInput) {
        this.userInput = userInput;
        this.matchedTasks = new TaskList();
    }

    /**
     * Executes the FindCommand which finds tasks containing the keyword typed by the user.
     *
     * @param tasks The list where tasks are added to.
     * @param ui The ui to print out response from JamesBot.
     * @param storage The task list that is stored in the storage file.
     * @throws JamesException If keyword is blank.
     */

    public String execute(TaskList tasks, Ui ui, Storage storage) throws JamesException {
        boolean hasNoKeyword = userInput.toLowerCase().replaceFirst(COMMAND, "").isBlank();
        if (hasNoKeyword) {
            throw new JamesException("Keyword is missing!\n"
                    + MESSAGE_FORMAT);
        }

        String keyword = userInput.substring(COMMAND.length()).trim();
        int tasksLength = tasks.size();

        for (int i = 0; i < tasksLength; i++) {
            if (tasks.get(i).toString().contains(keyword)) {
                matchedTasks.add(tasks.get(i));
            }
        }

        return ui.displayFoundTask(matchedTasks);
    }

    /**
     * Returns whether FindCommand exits the program.
     *
     * @return false as FindCommand does not exit the program.
     */
    public boolean isExit() {
        return false;
    }
}
