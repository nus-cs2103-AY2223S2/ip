package duke.command;

import duke.Parser;
import duke.TaskList;
import duke.exception.DukeException;
import duke.exception.EmptyTaskDescriptionException;
import duke.exception.InvalidDateTimeException;
import duke.task.Deadline;
import duke.task.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

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
            int doneByIndex = contents.indexOf("/by");
            String description = contents.substring(9, doneByIndex - 1);
            String doneByString = contents.substring(doneByIndex + 4);
            System.out.println("c");
            LocalDateTime doneBy = Parser.parseDateTime(doneByString);
            Task task = new Deadline(description, doneBy);
            tasks.addTask(task);
            return tasks.addTaskText(task);
        } catch (StringIndexOutOfBoundsException e) {
            throw new EmptyTaskDescriptionException();
        } catch (DateTimeParseException e ) {
            throw new InvalidDateTimeException();
        }
    }
}
