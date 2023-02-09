package chattime.command;

import java.util.function.Supplier;
import java.util.stream.Stream;

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
            return findIn(taskList);
        }
    }

    /**
     * Find tasks in tasklist relevant to the given keyword.
     *
     * @param taskList Current task list storing tasks.
     * @return The find result of given keyword.
     */
    private String findIn(TaskList taskList) {
        String message = "Are you looking for this:";

        Supplier<Stream<Task>> filteredTask = () -> taskList.getList().stream()
                .filter(task -> task.isMatchDescription(keyword));

        message += filteredTask.get().map(Task::toString)
                .reduce("\n", (prevMsg, task) -> prevMsg + "\n     > " + task);

        int pending = (int) filteredTask.get().filter(task -> !task.getTaskStatus()).count();
        int totalResult = (int) filteredTask.get().count();

        message += "\n\nHere have " + totalResult + " result(s) with keyword \"" + keyword + "\".";
        message += (pending == 0
                ? "\n**Congrats on finishing all the tasks!**"
                : "\nStill have " + pending + " task(s) to go. @*@");

        if (totalResult == 0) {
            message = "Yay holidayy! Seems that you don't have any task related to \"" + keyword + "\" currently.";
        }

        return message;
    }
}
