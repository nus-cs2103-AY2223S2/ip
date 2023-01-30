package duke.command;

import duke.DukeException;
import duke.task.TaskList;
import duke.task.TodoTask;
import duke.util.Ui;

/**
 * A command that creates a TodoTask
 * @author Junyi
 */
public class TodoCommand extends Command {

    private final TaskList taskList;
    private final String taskDescription;
    private final Ui ui;

    /**
     * Constructor for TodoCommand.
     * Creates and insert a TodoTask.
     * @param taskList TaskList of Duke's tasks.
     * @param taskDescription Description of task.
     * @param ui Ui instance of Duke.
     */
    public TodoCommand(TaskList taskList, String taskDescription, Ui ui) {
        this.taskList = taskList;
        this.taskDescription = taskDescription;
        this.ui = ui;
    }

    @Override
    public String execute() throws DukeException {
        TodoTask task = new TodoTask(taskDescription);
        taskList.addTask(task);
        return ui.showTaskCreatedMessage(task);
    }
}
