package duke.commands;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.Ui;

/**
 * Encapsulates todo command and its arguments.
 */
public class TodoCommand extends AddCommand {
    public static final String COMMAND = "todo";
    private String[] tokens;

    /**
     * Constructs a new Todo Command.
     *
     * @param tokens Arguments of the command.
     */
    public TodoCommand(String[] tokens) {
        this.tokens = tokens;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String description = tokens[1];
        Task newTask = new Todo(description);
        tasks.addTask(newTask, storage);
        ui.addToResponseMessage("Got it. I've added this task:");
        ui.addToResponseMessage("  " + newTask);
        ui.addToResponseMessage("Now you have " + tasks.size() + " tasks in the list.");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
