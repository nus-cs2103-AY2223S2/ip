package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.stream.IntStream;

/**
 * Command to list all tasks.
 */
public class ListCommand implements Command {
    @Override
    public String getCommandName() {
        return "list";
    }

    @Override
    public String getCommandRegexPattern() {
        return "^list$";
    }

    @Override
    public String getCommandPattern() {
        return "list";
    }

    /**
     * Lists tasks in task list.
     *
     * @param ui       User interface.
     * @param taskList Task list.
     * @param storage  Storage.
     * @param args     Argument list; Does not take in any arguments.
     */
    @Override
    public void run(Ui ui, TaskList taskList, Storage storage, String... args) {
        ui.showLine();
        ui.showText("Here are the tasks in your list:");

        IntStream.rangeClosed(1, taskList.getTotalTasks())
                .forEach(taskNo -> ui.showText(
                        String.format("%d. %s", taskNo, taskList.getTask(taskNo).toString())));
        ui.showLine();
    }
}
