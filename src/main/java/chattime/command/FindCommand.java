package chattime.command;

import chattime.TaskList;
import chattime.storage.Storage;
import chattime.task.Task;
import chattime.ui.Ui;

/**
 * Represents FindCommand object that handles main logic of matching keyword with current tasks and return result list.
 */
public class FindCommand extends Command {

    private String keyword;

    /**
     * Creates FindCommand object to filter and display task list according to the keyword.
     *
     * @param input Specific keyword to find relevant tasks.
     */
    public FindCommand(String input) {
        keyword = input;
    }

    /**
     * Executes logic of displaying task list by filtering task objects on given keyword.
     *
     * @param ui UI instance of bot.
     * @param taskList Current task list storing tasks.
     * @param storage Storage file to store current state items of task list.
     * @return Bot's reply to user's find task command.
     */
    @Override
    public String execute(Ui ui, TaskList taskList, Storage storage) {
        if (taskList.getList().size() == 0) {
            return ui.warnEmptyList();

        } else {
            int i = 1;
            int total = 0;
            int pending = 0;
            String message = "I've matched the task(s) with your search keyword for you:";

            for (Task task : taskList.getList()) {
                if (task.isMatchDescription(keyword)) {
                    message = message.concat(String.format("\n     %d. %s", i, task));
                    i++;
                    total++;
                    if (!task.getTaskStatus()) {
                        pending++;
                    }
                }
            }

            message += "Here have " + total + " result(s) with keyword \""
                    + keyword + "\". Still have" + pending + " task(s) to go.";

            if (total == 0) {
                message = "Whoo! Seems that you don't have any task related to \"" + keyword + "\" currently.";
            }

            return message;
        }
    }
}
