package command;

import main.Storage;
import main.TaskList;
import main.Ui;
import sebastianExceptions.DateFormatMismatchException;
import sebastianExceptions.LackOfArgumentException;

import java.time.format.DateTimeParseException;

public class GetCommand extends Command{

    private String instruction;

    public GetCommand(String instruction) {
        this.instruction = instruction;
    }

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
