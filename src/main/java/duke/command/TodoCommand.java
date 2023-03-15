package duke.command;

import duke.task.Task;
import duke.task.Todo;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents a TodoCommand that implements the Command interface.
 */
public class TodoCommand implements Command {
    private String todoDescription;
    public TodoCommand(String todoDescription) {
        this.todoDescription = todoDescription;
    }
    @Override
    public String execute(Storage storage, TaskList tasks, Ui ui) {
        Task newTask = new Todo(todoDescription);
        assert newTask != null;
        tasks.addTask(newTask);
        String toDisplay = String.format("Gotcha! I have added this task:\n%s\nNow you have a total of %d tasks.",
                newTask, tasks.getSize());
        ui.displayMessage(toDisplay);
        return toDisplay;
    }
}
