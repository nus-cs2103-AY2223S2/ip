package duke.Command;

import duke.Utilities.TaskList;
import duke.Utilities.UI;
import duke.Utilities.Storage;

import duke.Exception.InvalidArgumentsException;

import duke.Task.Deadline;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The Deadline command which is executed by duke.Utilities.Duke.
 */
public class DeadlineCommand extends Command {

    private final String name;
    private LocalDateTime deadline;

    /**
     * The constructor for an (executable) DeadlineCommand.
     * @param name Name of deadline.
     * @param deadline Time at which deadline is due.
     * @throws InvalidArgumentsException Exception thrown when the date/time input is given in an invalid format.
     */
    public DeadlineCommand(String name, String deadline) throws InvalidArgumentsException {
        this.name = name;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            this.deadline = LocalDateTime.parse(deadline.trim(), formatter);
        } catch (DateTimeParseException e) {
            throw new InvalidArgumentsException();
        }
    }

    /**
     * Creates a new Deadline task which is added to the task list.
     * The confirmation is then displayed on the UI, and the changes to task list are saved to file.
     * @param tasks The task list to add to/edit/delete from.
     * @param ui The UI to display the confirmation messages/error messages to users.
     * @param storage The storage which to store to when a task is added/deleted or its status is changed.
     */
    @Override
    public String execute(TaskList tasks, UI ui, Storage storage) {
        Deadline dl = new Deadline(name, deadline);
        String confirmationMessage = tasks.addTask(dl);
        storage.saveToFile(tasks.getTasks());
        return confirmationMessage;
    }

    @Override
    public boolean isByeCommand() {
        return false;
    }
}
