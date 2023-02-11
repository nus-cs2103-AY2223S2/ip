package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class ListCommand extends Command{
    private String input;

    /**
     * Constructor for command "List".
     *
     * @param input The user's input.
     */
    public ListCommand(String input) {
        this.input = input;
    }

    public String execute(TaskList tasks, Ui ui, Storage storage) {
        StringBuilder listOfTasks = new StringBuilder("Here are your tasks: \n");
        for (int i = 0; i < tasks.getSize(); i++) {
            listOfTasks.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }
        return listOfTasks.toString();
    };
}
