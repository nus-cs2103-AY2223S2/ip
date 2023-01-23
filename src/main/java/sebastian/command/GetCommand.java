package sebastian.command;

import sebastian.main.Storage;
import sebastian.main.TaskList;
import sebastian.main.Ui;
import sebastian.sebastianExceptions.DateFormatMismatchException;
import sebastian.sebastianExceptions.LackOfArgumentException;

import java.time.format.DateTimeParseException;

public class GetCommand extends Command{

    private String instruction;

    public GetCommand(String instruction) {
        this.instruction = instruction;
    }

    /**
     * Retrieve the tasks occurring on a specific date
     * @param taskList taskList instance created at the start of the session
     * @param ui ui instance created at the start of the session
     * @param storage storage instance created at the start of the session
     * @throws LackOfArgumentException when user did not indicate a date
     * @throws DateFormatMismatchException when user attempts to specify a data with the wrong format
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage)
            throws LackOfArgumentException,
            DateFormatMismatchException
    {
        String[] insArr = instruction.split(" ");
        if(insArr.length == 1) {
            throw new LackOfArgumentException();
        } else if(insArr.length == 2) {
            try {
                String res = taskList.getTasksOnDate(insArr[1]).toString();
                ui.printFormattedString(res);
            } catch (DateTimeParseException e) {
                throw new DateFormatMismatchException();
            }
        } else {
            throw new DateFormatMismatchException();
        }
    }
}
