package cluck.commands;

import cluck.messages.Messages;
import cluck.storage.Storage;
import cluck.tasklist.TaskList;

/**
 * The Find task command returns a list of Tasks with descriptions containing the given keyword.
 */
public class FindTaskCommand implements Command {
    private final String keyWord;

    /**
     * Instantiates a new Find task command.
     *
     * @param keyWord the key word
     */
    public FindTaskCommand(String keyWord) {
        this.keyWord = keyWord;
    }

    @Override
    public String execute(TaskList taskList, Storage storage) {
        TaskList matchingTasks = taskList.findMatches(keyWord);
        if (matchingTasks.taskCount() == 0) {
            return Messages.MESSAGE_NO_MATCHES_FOUND;
        }
        if (matchingTasks.taskCount() == 1) {
            return Messages.MESSAGE_ONE_MATCH_FOUND + "\n" + matchingTasks;
        }
        return Messages.MESSAGE_MATCHES_FOUND + "\n" + matchingTasks;
    }
}
