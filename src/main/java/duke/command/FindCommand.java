package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class FindCommand extends Command {
    private String input;

    /**
     * Constructor for command "Find".
     *
     * @param input The user's input.
     */
    public FindCommand(String input) {
        this.input = input;
    }

    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String keyword = input.substring(5);
        int counter = 0;
        StringBuilder matchingTasks = new StringBuilder("Here are the matching tasks in your list:\n");
        for (int i = 0; i < tasks.getSize(); i++) {
            Task findTask = tasks.get(i);
            if (findTask.contains(keyword)) {
                counter++;
                matchingTasks.append(counter).append(". ").append(findTask).append("\n");
            }
        }
        return matchingTasks.toString();
    };
}
