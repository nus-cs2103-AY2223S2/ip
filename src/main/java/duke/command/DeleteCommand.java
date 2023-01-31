package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * DeleteCommands help calls a function to deletes a task given the index
 */
public class DeleteCommand extends Command{

    protected int index;

    /**
     * constructor of the DeleteCommand
     * @param index of task that is to be delete
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * executes the purpose of the DeleteCommand
     * @param taskList is where a task will be deleted from the list
     * @param storage can be ignored but is required due to the abstract class
     * @param ui handles the displaying of the bot outputs
     */
    @Override
    public void executeCommand(TaskList taskList, Storage storage, Ui ui) {
        ui.printText(" Noted. I've removed this task:");

        taskList.deleteTask(index, ui);
        ui.printText("Now you have " + taskList.getNumberOfTask() + " tasks in the list.");

    }

    /**
     * indication if the command should end the program
     * @return false as addCommand is not an ExitCommand
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
