package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * AddCommands help calls the function add a new Task object be it Task, Event or Deadline into the task list.
 */
public class AddCommand extends Command {

    protected Task task;

    /**
     * constructor for AddCommand
     * @param task represent the either the to do task, event task or deadline task.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * executes the purpose of the AddCommand
     * @param taskList is where a task will be added into the list
     * @param storage can be ignored but is required due to the abstract class
     * @param ui handles the displaying of the bot outputs
     */
    @Override
    public void executeCommand(TaskList taskList, Storage storage, Ui ui) {
        String output = "Got it. I've added this task:";
        taskList.addTask(task);

        output = output + "\n " + task;

        ui.printText(output + "\n" + taskList.getNumberOfTask() + " tasks in the list.");

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
