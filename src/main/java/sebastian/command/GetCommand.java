package sebastian.command;

import java.time.format.DateTimeParseException;

import sebastian.exceptions.GetFormatMismatchException;
import sebastian.exceptions.LackOfArgumentException;
import sebastian.main.Storage;
import sebastian.main.TaskList;
import sebastian.main.Ui;

/**
 * Class used to handle a command to retrieve tasks occurring on a certain date
 */
public class GetCommand extends Command {

    private String instruction;

    public GetCommand(String instruction) {
        this.instruction = instruction;
    }

    /**
     * Retrieve the tasks occurring on a specific date
     * @param taskList taskList instance created at the start of the session
     * @param ui ui instance created at the start of the session
     * @param storage storage instance created at the start of the session
     * @return a string representing the result of task execution
     * @throws LackOfArgumentException when user did not indicate a date
     * @throws GetFormatMismatchException when user attempts to specify a data with the wrong format
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage)
            throws LackOfArgumentException, GetFormatMismatchException {
        String[] insArr = instruction.split(" ");
        if (insArr.length == 1) {
            throw new LackOfArgumentException("Please specify a date");
        } else if (insArr.length == 2) {
            try {
                String res = taskList.getTasksOnDate(insArr[1]).toString();
                return ui.getFormattedString(res);
            } catch (DateTimeParseException e) {
                throw new GetFormatMismatchException();
            }
        } else if (insArr.length > 2) {
            throw new GetFormatMismatchException();
        } else {
            throw new Error("Internal Error");
        }
    }
}
