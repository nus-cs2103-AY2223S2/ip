package duke.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.util.DateTimeUtils;

/**
 * Command to add deadline task.
 */
public class DeadlineCommand extends AddTaskCommand {
    @Override
    public String getCommandName() {
        return "deadline";
    }

    @Override
    public String getCommandRegexPattern() {
        return "deadline (.*) \\/place (.*) \\/by (.*)";
    }

    @Override
    public String getCommandPattern() {
        return "deadline <description> /place <location> /by <deadline>";
    }

    /**
     * Adds deadline task from input to the task list.
     *
     * @param ui       User interface.
     * @param taskList Task list.
     * @param storage  Storage.
     * @param args     Argument list in order: description, place, by.
     * @throws DukeException If failed to save new task list to storage or invalid datetime format.
     */
    @Override
    public void run(Ui ui, TaskList taskList, Storage storage, String... args) throws DukeException {
        // Assert arguments has only 3 items: description, place, by.
        assert args.length == 3;

        String description = args[0];
        String place = args[1];
        String by = args[2];

        try {
            LocalDateTime byDateTime = LocalDateTime.parse(by, DateTimeUtils.DATE_TIME_FORMAT_INPUT);
            addTask(new Deadline(description, place, byDateTime), ui, taskList, storage);
        } catch (DateTimeParseException exception) {
            throw new DukeException("Please enter date and time in this format: dd/MM/yyyy HH:mm");
        }
    }
}
