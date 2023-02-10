package duke.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Event;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.util.DateTimeUtils;

/**
 * Command to add an event.
 */
public class EventCommand extends AddTaskCommand {
    @Override
    public String getCommandName() {
        return "event";
    }

    @Override
    public String getCommandRegexPattern() {
        return "^event (.*) \\/place (.*) \\/from (.*) \\/to (.*)$";
    }

    @Override
    public String getCommandPattern() {
        return "event <description> /place <location> /from <from> /to <to>";
    }

    /**
     * Adds event task from input to the task list.
     *
     * @param ui       User interface.
     * @param taskList Task list.
     * @param storage  Storage.
     * @param args     Argument list in order: place, description, from, to.
     * @throws DukeException If failed to save new task list to storage or invalid date time
     *                       format.
     */
    @Override
    public void run(Ui ui, TaskList taskList, Storage storage, String... args) throws DukeException {
        // Assert arguments has only 4 items: description, place, from, to.
        assert args.length == 4;

        String description = args[0];
        String place = args[1];
        String from = args[2];
        String to = args[3];

        try {
            LocalDateTime fromDateTime = LocalDateTime.parse(from, DateTimeUtils.DATE_TIME_FORMAT_INPUT);
            LocalDateTime toDateTime = LocalDateTime.parse(to, DateTimeUtils.DATE_TIME_FORMAT_INPUT);

            addTask(new Event(description, place, fromDateTime, toDateTime), ui, taskList, storage);
        } catch (DateTimeParseException exception) {
            throw new DukeException("Please enter date and time in this format: dd/MM/yyyy HH:mm");
        }
    }
}
