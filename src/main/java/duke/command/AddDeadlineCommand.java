package duke.command;

import java.time.format.DateTimeParseException;

import duke.Parser;
import duke.TaskList;
import duke.exception.DukeException;
import duke.exception.IncompleteCommandException;
import duke.exception.InvalidDateTimeException;
import duke.task.Deadline;

/**
 * A command representing the user adding a new Deadline to the task list.
 */
public class AddDeadlineCommand extends Command {
    private String contents;

    /**
     * A constructor for AddDeadlineCommand.
     *
     * @param tasks TaskList object to add Deadline object to
     * @param contents Description and deadline of task
     */
    public AddDeadlineCommand(TaskList tasks, String contents) {
        super(tasks);
        this.contents = contents;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String execute() throws DukeException {
        try {
            Deadline task = Parser.parseDeadline(contents);
            tasks.addTask(task);
            return tasks.addTaskText(task);
        } catch (StringIndexOutOfBoundsException e) {
            throw new IncompleteCommandException("deadline");
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeException();
        }
    }
}
