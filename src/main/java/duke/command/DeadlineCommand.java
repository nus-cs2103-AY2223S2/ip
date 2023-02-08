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
        return "deadline (.*) \\/by (.*)";
    }

    @Override
    public String getCommandPattern() {
        return "deadline <description> /by <deadline>";
    }

    /**
     * Adds deadline task from input to the task list.
     *
     * @param ui       User interface.
     * @param taskList Task list.
     * @param storage  Storage.
     * @param args     Argument list in order: description, by.
     * @throws DukeException If failed to save new task list to storage or invalid datetime format.
     */
    @Override
    public void run(Ui ui, TaskList taskList, Storage storage, String... args) throws DukeException {
        // Assert arguments has only 2 items: description, by.
        assert args.length == 2;

        String description = args[0];
        String by = args[1];

        try {
            LocalDateTime byDateTime = LocalDateTime.parse(by, DateTimeUtils.DATE_TIME_FORMAT_INPUT);
            addTask(new Deadline(description, byDateTime), ui, taskList, storage);
        } catch (DateTimeParseException exception) {
            throw new DukeException("Please enter date and time in this format: dd/MM/yyyy HH:mm");
        }
    }
}
