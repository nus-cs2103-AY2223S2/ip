package cluck.commands;

import cluck.messages.Messages;
import cluck.taskList.TaskList;
import cluck.tasks.Task;

import java.util.ArrayList;

public class FindTaskCommand implements Command {
    private final String keyWord;

    public FindTaskCommand(String keyWord) {
        this.keyWord = keyWord;
    }

    @Override
    public String execute(TaskList taskList) {
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
