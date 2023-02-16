package duke.command;

import duke.Parser;
import duke.TaskList;
import duke.exception.DukeException;
import duke.exception.EmptyTaskDescriptionException;
import duke.exception.InvalidDateTimeException;
import duke.tag.Tag;
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
        Task task;
        try {
            int doneByIndex = contents.indexOf("/by");
            String description = contents.substring(9, doneByIndex - 1);
            if (!contents.contains("/tag")){
                String doneByString = contents.substring(doneByIndex + 4);
                LocalDateTime doneBy = Parser.parseDateTime(doneByString);
                task = new Deadline(description, doneBy);
            } else {
                int tagIndex = contents.indexOf("/tag");
                String doneByString = contents.substring(doneByIndex + 4, tagIndex - 1);
                LocalDateTime doneBy = Parser.parseDateTime(doneByString);
                String tagName = contents.substring(tagIndex + 5).trim();
                Tag tag = new Tag(tagName);
                task = new Deadline(description, doneBy, tag);
            }
            tasks.addTask(task);
            return tasks.addTaskText(task);
        } catch (StringIndexOutOfBoundsException e) {
            throw new EmptyTaskDescriptionException();
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeException();
        }
    }
}
