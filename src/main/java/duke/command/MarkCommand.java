package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * MarkCommand help calls the function to mark a task
 */
public class MarkCommand extends Command {
    protected int index;

    /**
     * constructor of MarkCommand
     * @param index of the task to be marked
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * executes the purpose of the MarkCommand
     * @param taskList to mark the index task
     * @param storage can be ignored but is required due to the abstract class
     * @param ui handles the displaying of the bot outputs
     */
    @Override
    public void executeCommand(TaskList taskList, Storage storage, Ui ui) {

        taskList.markTask(index);
        Task task = taskList.getTasks().get(index-1);

        ui.printText("Nice! I've marked this task as done:\n " + task.toString());
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
        String output = "mark taskIndex" +
                "\nThis command marks the task as complete" +
                "\nExample: mark 3";

        return output;
    }
}
