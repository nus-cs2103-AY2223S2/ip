package panav.command;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import panav.storage.Storage;
import panav.task.Deadline;
import panav.task.Task;
import panav.task.TaskList;
import panav.ui.Ui;



/**
 * Class to encapsulate a 'deadline' command, extends from Command.
 */
public class DeadlineCommand extends Command {

    public static final String COMMAND_WORD = "deadline";
    private String deadlineMessage;
    private String deadline;

    /**
     * Constructor to initialise attributes.
     * @param deadlineMessage
     * @param deadline
     */
    public DeadlineCommand(String deadlineMessage, String deadline) {
        this.deadlineMessage = deadlineMessage;
        this.deadline = deadline;
    }

    /**
     * Specifies the behaviour of 'deadline' command when called to execute. Formats the
     * date and adds the new Deadline task to list of tasks.
     * @param tasks the list of tasks.
     * @param ui ui to interact with user.
     * @param storage storage to read/write text in file.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {

        deadline = formatDate(deadline);
        Task curr = new Deadline(deadlineMessage, deadline);
        tasks.addTask(curr);
        return ui.showAddTaskMessage(tasks, curr);

    }

    /**
     * Formats the date according to 'MMM dd yyyy' if it's given in the correct format of
     * 'yyyy-mm-dd', otherwise it just returns the string.
     *
     * @param dateString the string to be formatted.
     * @return either the formatted date or the original string itself.
     */
    public static String formatDate(String dateString) {
        LocalDate d = null;
        String result = dateString;
        try {
            d = LocalDate.parse(dateString);
            result = d.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (DateTimeParseException e) {
            return result;
        }
        return result;
    }

    @Override
    public String toString() {
        return DeadlineCommand.COMMAND_WORD;
    }
}
