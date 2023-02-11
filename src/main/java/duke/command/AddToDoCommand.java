package duke.command;

import duke.interfaces.Command;
import duke.model.Task;
import duke.interfaces.View;
import duke.model.TaskModel;

/**
 * A command that adds a todo when executed.
 */
public class AddToDoCommand implements Command {
    private final TaskModel taskModel;
    private final View taskView;
    private final String todoDescription;
    private static final String addedTaskMessage = "Got it. I've added this task:\n  ";

    /**
     * Instantiates a command that creates a todo when it is executed.
     * @param taskView The current view.
     * @param taskModel The model that stores the task list.
     * @param todoDescription A description of the todo.
     */
    AddToDoCommand(View taskView, TaskModel taskModel, String todoDescription) {
        this.todoDescription = todoDescription;
        this.taskView = taskView;
        this.taskModel = taskModel;
    }

    /**
     * Creates a todo with the description provided.
     */
    @Override
    public void execute() {
        Task newTask = this.taskModel.createTask(todoDescription);
        taskView.showMessage(addedTaskMessage + newTask.toString()
        + String.format("\nNow you have %d tasks in the list.", taskModel.getNumberOfTasks()));
    }
}
