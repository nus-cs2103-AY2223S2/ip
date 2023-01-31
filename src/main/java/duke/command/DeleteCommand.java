package duke.command;

import duke.dukeexception.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.io.IOException;

/**
 * Command that handles deleting a Task
 */
public class DeleteCommand extends Command {
    private int taskNo;

    /**
     * Constructor that passes in the number of the task to be deleted
     * 
     * @param n
     */
    public DeleteCommand(int n) {
        this.taskNo = n;
    }

    /**
     * on execution, delete the target task from the TaskList and print out customised message
     * 
     * @param tasks TaskList to be updated if needed
     * @param ui Ui for displaying messages in a unique way
     * @param storage Storage for updating local tasks
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            ui.printToFormat("The following task is removed:\n    " + tasks.remove(taskNo));
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
        try {
            storage.update(tasks);
        } catch (IOException e) {
            System.out.println("failed to update tasks locally: " + e.getMessage());
        }
    }
}
