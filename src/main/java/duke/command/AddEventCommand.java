package duke.command;

import java.time.format.DateTimeParseException;

import duke.Parser;
import duke.TaskList;
import duke.exception.DukeException;
import duke.exception.IncompleteCommandException;
import duke.exception.InvalidDateTimeException;
import duke.task.Event;

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
        try {
            Event task = Parser.parseEvent(contents);
            tasks.addTask(task);
            return tasks.addTaskText(task);
        } catch (StringIndexOutOfBoundsException e) {
            throw new IncompleteCommandException("event");
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeException();
        }
    }
}
