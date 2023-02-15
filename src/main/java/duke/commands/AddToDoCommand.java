package duke.commands;

import java.io.IOException;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;
import duke.utils.Formatter;


/**
 * Represents a Command that only adds ToDo tasks to the TaskList.
 * */
public class AddToDoCommand extends Command {
    private final String description;

    /**
     * Constructs an AddToDoCommand
     * @param description of the task
     */
    public AddToDoCommand(String description) {
        this.description = description;
    }

    /**
     * Creates and adds a new ToDo task to the TaskList.
     * Prompts Ui to notify the user, then prompts Storage class to append the new task to the task storage file.
     * @param tasks Existing TaskList used by the main Duke class
     * @param storage Existing Storage used by the main Duke class
     * @return output to be shown to user
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        assert tasks != null;
        try {
            Task newTask = new ToDo(description);
            tasks.add(newTask);
            storage.appendToFile(newTask);
            return Formatter.formatAddTask(newTask, tasks.getSize());
        } catch (IOException e) {
            return "WRITING... ERROR! Unable to write to file. Please run Duke again.";
        }
    }
}
