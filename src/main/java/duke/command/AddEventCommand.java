package duke.command;

import duke.Parser;
import duke.TaskList;
import duke.exception.DukeException;
import duke.task.Event;
import duke.task.Task;

/**
 * A command representing the user adding a new Event to the task list.
 */
public class AddEventCommand extends Command {
    private String contents;

    /**
     * A constructor for AddEventCommand.
     *
     * @param tasks TaskList object to add Event object to
     * @param contents Description, start and end times of task
     */
    public AddEventCommand(TaskList tasks, String contents) {
        super(tasks);
        this.contents = contents;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String execute() throws DukeException {
        int startIndex = contents.indexOf("/from");
        int endIndex = contents.indexOf("/to");
        String description = contents.substring(6, startIndex - 1);
        String startString = contents.substring(startIndex + 6, endIndex - 1);
        String endString = contents.substring(endIndex + 4);
        Task task = new Event(description, Parser.parseDateTime(startString), Parser.parseDateTime(endString));
        tasks.addTask(task);
        return tasks.addTaskText(task);
    }
}
