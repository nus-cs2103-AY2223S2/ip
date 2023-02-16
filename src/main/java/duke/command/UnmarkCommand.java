package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class UnmarkCommand extends Command{
    protected int index;

    /**
     * constructor for the UnmarkCommand
     * @param index to unmark the index task
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * executes the purpose of the UnmarkCommand
     * @param taskList to unmark the index task
     * @param storage can be ignored but is required due to the abstract class
     * @param ui handles the displaying of the bot outputs
     */
    @Override
    public void executeCommand(TaskList taskList, Storage storage, Ui ui) {
        taskList.unmarkTask(index);
        Task task = taskList.getTasks().get(index - 1);

        ui.printText("OK, I've marked this task as not done yet:\n " + task);
    }

    /**
     * indication if the command should end the program
     * @return false as addCommand is not an exitCommand
     */
    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String toString() {
        String output = "unmark taskIndex" +
                "\nThis commands unmark the task of the index as incomplete" +
                "\nExample: unmark 3";

        return output;
    }
}
