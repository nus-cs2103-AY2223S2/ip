package duke.command;

import duke.DukeException;
import duke.FormatChecker;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Command that is used to add a deadline task when executed.
 */
public class DeadlineCommand extends Command {
    private String content;

    /**
     * Class constructor of DeadlineCommand.
     * @param content the content of the deadline task that consists of title and deadline.
     */
    public DeadlineCommand(String content) {
        this.content = content;
    }

    /**
     * Executes the DeadlineCommand to add a deadline task into the given TaskList.
     * @param tasks the TaskList of the Duke
     * @param ui the Ui of the Duke
     * @param storage the storage of the Duke
     * @return the message to indicate addition of the deadline task
     * @throws DukeException if error occurs during addition of the deadline task
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        assert FormatChecker.isCorrectDeadlineCmd(content) == true
                : "Please use the correct format to add a deadline.";
        String res = "";
        String[] dlTask = content.split("/by");
        String title = dlTask[0].trim();
        String deadline = dlTask[1].trim();
        assert FormatChecker.isCorrectDateInput(dlTask[1]) == true
                : "Please use the correct format for date (dd/MM/yyyy HH:mm)";
        res = tasks.addDeadline(title, deadline);
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
