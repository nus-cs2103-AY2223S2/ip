package duke.command;

import duke.Parser;
import duke.TaskList;
import duke.exception.DukeException;
import duke.exception.EmptyTaskDescriptionException;
import duke.exception.InvalidDateTimeException;
import duke.exception.InvalidTaskDurationException;
import duke.tag.Tag;
import duke.task.Event;
import duke.task.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

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

    private void checkValidStartEnd(LocalDateTime start, LocalDateTime end) throws InvalidTaskDurationException {
        if (start.isAfter(end)) {
            throw new InvalidTaskDurationException();
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public String execute() throws DukeException {
        try {
            int startIndex = contents.indexOf("/from");
            int endIndex = contents.indexOf("/to");
            String description = contents.substring(6, startIndex - 1);
            String startString = contents.substring(startIndex + 6, endIndex - 1);
            LocalDateTime start = Parser.parseDateTime(startString);
            Task task;
            if (!contents.contains("/tag")) {
                String endString = contents.substring(endIndex + 4);
                LocalDateTime end = Parser.parseDateTime(endString);
                checkValidStartEnd(start, end);
                task = new Event(description, start, end);
            } else {
                int tagIndex = contents.indexOf("/tag");
                String endString = contents.substring(endIndex + 4, tagIndex - 1);
                LocalDateTime end = Parser.parseDateTime(endString);
                checkValidStartEnd(start, end);
                String tagName = contents.substring(tagIndex + 5).trim();
                Tag tag = new Tag(tagName);
                task = new Event(description, start, end, tag);
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
