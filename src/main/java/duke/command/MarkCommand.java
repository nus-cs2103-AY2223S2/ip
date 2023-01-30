package duke.command;

import duke.dukeexception.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.io.IOException;

/**
 * Command that handles marking a specific Task as done
 */
public class MarkCommand extends Command {
    private int taskNo;

    /**
     * Constructor that takes in the number of the Task to be marked
     * 
     * @param n
     */
    public MarkCommand(int n) {
        this.taskNo = n;
    }

    /**
     * on execution, mark the target Task in the TaskList as done and print out customised message
     * 
     * @param tasks TaskList to be updated if needed
     * @param ui Ui for displaying messages in a unique way
     * @param storage Storage for updating local tasks
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            ui.printToFormat("Marked as completed:\n    " + tasks.mark(taskNo));
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
