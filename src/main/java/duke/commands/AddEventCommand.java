package duke.commands;

import java.io.IOException;

import duke.storage.Storage;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.utils.Formatter;

/**
 * Represents a Command that only adds Event tasks to the TaskList.
 * */
public class AddEventCommand extends Command {
    private final String description;
    private final String from;
    private final String to;

    /**
     * Constructs an AddEventCommand
     * @param description of task
     * @param from start time or date of event
     * @param to end time or date of event
     */
    public AddEventCommand(String description, String from, String to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    /**
     * Creates and adds a new Event task to the TaskList.
     * Prompts Ui to notify the user, then prompts Storage class to append the new task to the task storage file.
     * @param tasks Existing TaskList used by the main Duke class.
     * @param storage Existing Storage used by the main Duke class.
     * @return output to be shown to user
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        assert tasks != null;
        try {
            Task newTask = new Event(description, from, to);
            tasks.add(newTask);
            storage.appendToFile(newTask);
            return Formatter.formatAddTask(newTask, tasks.getSize());
        } catch (IOException e) {
            return "WRITING... ERROR! Unable to write to file. Please run Duke again.";
        }
    }
}
