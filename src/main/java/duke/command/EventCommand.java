package duke.command;

import duke.DukeException;
import duke.FormatChecker;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Command that is used to add an event task when executed.
 */
public class EventCommand extends Command {
    private String content;

    /**
     * Class constructor of EventCommand.
     * @param content the content of the event task that consists of title and event duration.
     */
    public EventCommand(String content) {
        this.content = content;
    }

    /**
     * Executes the EventCommand to add an event task into the given TaskList.
     * @param tasks the TaskList of the Duke
     * @param ui the Ui of the Duke
     * @param storage the storage of the Duke
     * @return the message to indicate addition of the event task
     * @throws DukeException if error occurs during addition of the event task
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        assert FormatChecker.isCorrectEventCmd(this.content) == true
                : "Please use the correct format to add an event.";
        String res = "";
        String[] eventTask = content.split("/from|/to");
        String title = eventTask[0].trim();
        String fromDateTime = eventTask[1].trim();
        String toDateTime = eventTask[2].trim();
        assert FormatChecker.isCorrectDateInput(fromDateTime) == true
                && !FormatChecker.isCorrectDateInput(toDateTime) == true
                : "Please use the correct format for date (dd/MM/yyyy HH:mm)";
        res += tasks.addEvent(title, fromDateTime, toDateTime);
        return res;
    }

    /**
     * Returns true when the command indicates the closure of the software.
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
