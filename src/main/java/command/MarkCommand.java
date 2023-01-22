package command;

import main.Storage;
import main.TaskList;
import main.Ui;
import sebastianExceptions.CannotWriteDataException;
import sebastianExceptions.InstructionFormatMismatchException;
import sebastianExceptions.LackOfArgumentException;
import sebastianExceptions.TaskNotExistException;

public class MarkCommand extends Command{

    private final String instruction;

    public MarkCommand(String instruction) {
        this.instruction = instruction;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage)
            throws LackOfArgumentException,
            InstructionFormatMismatchException,
            TaskNotExistException,
            CannotWriteDataException
    {
        String[] insArr = instruction.split(" ");
        if(insArr.length == 1) {
            throw new LackOfArgumentException();
        }
        else if(insArr.length == 2) {
            try {
                int taskIndex = Integer.parseInt(insArr[1]);
                String res =  "Well Done. I have marked this task as done: " + "\n" +
                        taskList.markTaskAtIndex(taskIndex) + "\n" +
                        "You can proceed with other tasks now";
                ui.printFormattedString(res);
                storage.writeToDisk(taskList);
            } catch (NumberFormatException e) {
                throw new InstructionFormatMismatchException("mark");
            } catch (IndexOutOfBoundsException e) {
                throw new TaskNotExistException();
            }
        }
        else {
            throw new InstructionFormatMismatchException("mark");
        }
    }
}
