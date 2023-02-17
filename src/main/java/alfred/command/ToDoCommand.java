package alfred.command;

import alfred.exceptions.AlfredException;
import alfred.storage.Storage;
import alfred.task.Task;
import alfred.task.TaskList;
import alfred.task.ToDo;
import alfred.ui.Ui;

/**
 * Represents the todo command when a user wishes to add a ToDo.
 */
public class ToDoCommand extends Command {

    private final String controlLine;

    /**
     * Constructs a Todo command with the given control line.
     * @param controlLine The remaining description of the task after the command word.
     */
    public ToDoCommand(String controlLine) {
        this.controlLine = controlLine;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws AlfredException {
        Task task = new ToDo(controlLine);
        tasks.addTask(task);

        String numTasks = tasks.getSize() == 1 ? "task" : "tasks";
        String output = String.format("Noted, task added:\n" + ui.getIndent() + "%s\n"
                + "Number of %s in the list: %d\n", task, numTasks, tasks.getSize());
        return ui.getCommandMessage(output);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
