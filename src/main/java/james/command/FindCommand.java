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

    public static final String MESSAGE = COMMAND + ": finds tasks containing the keywords.\n"
            + "(e.g find book)";

    public static final String MESSAGE_FORMAT = "to find a task containing keywords, "
            + "use the following format:\n'find [keywords]'\nhere is an example, 'find book'";

    private String userCommand;
    private TaskList matchedTasks;

    /**
     * Constructs a FindCommand object.
     *
     * @param userCommand The user input.
     */
    public FindCommand(String userCommand) {
        this.userCommand = userCommand;
        this.matchedTasks = new TaskList();
    }

    /**
     * Executes the FindCommand which finds tasks containing the keyword typed by the user.
     *
     * @param tasks The list where tasks are added to.
     * @param ui The ui to print out JamesBot's response.
     * @param storage The task list that is stored in the user's hard disk.
     * @throws JamesException If keyword is blank.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws JamesException {
        boolean hasNoKeyword = userCommand.toLowerCase().replaceFirst(COMMAND, "").isBlank();
        if (hasNoKeyword) {
            throw new JamesException("Keyword is missing!\n"
                    + MESSAGE_FORMAT);
        }

        String keyword = userCommand.substring(COMMAND.length()).trim();
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
