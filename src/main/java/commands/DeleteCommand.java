package commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import tasks.Task;

/**
 * Represents the command that deletes an existing task from the tasklist.
 */
public class DeleteCommand extends Command {
    private int taskIndex;

    /**
     * Constructs a new DeleteCommand with the specified index of the task.
     *
     * @param taskIndex the index of the task to be deleted in the tasklist
     */
    public DeleteCommand(int taskIndex) {
        super(CommandType.DELETE);
        this.taskIndex = taskIndex;
    }

    /**
     * Executes this DeleteCommand with a specified TaskList, Ui, and Storage.
     * Deletes the specified Task in the TaskList using the taskIndex, informs the Ui to display the deleted task,
     * and saves the updated TaskList to the file using the Storage object.
     *
     * @param list the TaskList to delete the Task from
     * @param ui the Ui to help inform the user of the deletion
     * @param storage the Storage to save the updated TaskList to
     */
    @Override
    public void execute(TaskList list, Ui ui, Storage storage) {
        if (taskIndex >= list.getSize()) {
            System.out.println("Oopsies.. Seems like that task does not exist :(");
        } else {
            Task toDelete = list.getTask(taskIndex);
            list.deleteTask(taskIndex);
            ui.printOutput("Ok. I've removed this task:\n\t\t"
                    + toDelete.toString() + "\n\t Now you have "
                    + list.getSize() + " tasks in the list.");
            storage.saveListToFile(list, ui);
        }
    }
}
