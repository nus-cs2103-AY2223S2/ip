package sebastian.command;

import sebastian.main.Storage;
import sebastian.main.TaskList;
import sebastian.main.Ui;
import sebastian.sebastianExceptions.CannotWriteDataException;
import sebastian.sebastianExceptions.InstructionFormatMismatchException;
import sebastian.sebastianExceptions.LackOfArgumentException;
import sebastian.sebastianExceptions.TaskNotExistException;

public class UnmarkCommand extends Command{

    private String instruction;

    public UnmarkCommand(String instruction) {
        this.instruction = instruction;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage)
            throws LackOfArgumentException, InstructionFormatMismatchException,
            TaskNotExistException, CannotWriteDataException {
        String[] insArr = instruction.split(" ");
        if(insArr.length == 1) {
            throw new LackOfArgumentException();
        }
        else if(insArr.length == 2) {
            try {
                int taskIndex = Integer.parseInt(insArr[1]);
                String res = "No problem, I have unmarked this task: " + "\n" +
                        taskList.unmarkTaskAtIndex(taskIndex) + "\n" +
                        "It's time to get started!";
                ui.printFormattedString(res);
                storage.writeToDisk(taskList);
            } catch (NumberFormatException e) {
                throw new InstructionFormatMismatchException("unmark");
            } catch (IndexOutOfBoundsException e) {
                throw new TaskNotExistException();
            }
        }
        else {
            throw new InstructionFormatMismatchException("unmark");
        }
    }
}
