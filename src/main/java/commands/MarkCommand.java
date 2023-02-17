package commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import tasks.Task;

/**
 * Represents the command that toggles the completion status
 * of a specific task in the tasklist.
 */
public class MarkCommand extends Command {
    private int taskIndex;

    /**
     * Constructs a new MarkCommand with the specified CommandType and taskIndex.
     *
     * @param type the type of this Command
     * @param taskIndex the index of the Task to be updated
     */
    public MarkCommand(CommandType type, int taskIndex) {
        super(type);
        this.taskIndex = taskIndex;
    }

    /**
     * Executes this MarkCommand with a specified TaskList, Ui, and Storage.
     * Toggles the completion status of a specific task as done or not done
     * depending on the CommandType of the current MarkCommand
     *
     * @param list the TaskList to retrieve the existing task from
     * @param ui the Ui to help inform the user of the update
     * @param storage the Storage to save the updated TaskList to
     */
    @Override
    public void execute(TaskList list, Ui ui, Storage storage) {
        if (taskIndex >= list.getSize()) {
            System.out.println("Oopsies.. Seems like that task does not exist :(");
        } else {
            Task currentTask = list.getTask(taskIndex);
            if (this.getType().equals(CommandType.MARK)) {
                currentTask.markAsDone();
            } else {
                currentTask.markAsNotDone();
            }
            ui.printOutput("Great :D I knew you could do it! I've marked this task as done:\n\t\t"
                    + currentTask.toString());
            storage.saveListToFile(list, ui);
        }
    }
}
