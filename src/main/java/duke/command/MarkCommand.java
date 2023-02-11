package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class MarkCommand extends Command {
    private String input;

    /**
     * Constructor for command "Mark".
     *
     * @param input The user's input.
     */
    public MarkCommand(String input) {
        this.input = input;
    }

    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String[] commandString = input.split(" ");
        int index = Integer.parseInt(commandString[1]) - 1;
        Task task = tasks.get(index);
        task.mark();
        storage.saveTasks(tasks);
        return "Nice! I've marked this task as done:\n" + task;
    };
}
