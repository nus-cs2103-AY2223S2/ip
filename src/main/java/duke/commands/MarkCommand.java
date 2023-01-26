package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

<<<<<<< Updated upstream
/**
 * This is a command that handles the marking and unmarking of a task
 */
=======
import java.io.IOException;

>>>>>>> Stashed changes
public class MarkCommand extends Command {
    private int taskNumber;
    private boolean isMarkingTask;

    /**
     * Constructor for MarkCommand class
     *
     * @param taskNumber the number representing the task to be marked
     * @param isMarkingTask whether the task should be marked or unmarked
     */
    public MarkCommand(int taskNumber, boolean isMarkingTask) {
        super();
        this.taskNumber = taskNumber;
        this.isMarkingTask = isMarkingTask;
    }

    /**
     * Checks if Duke should terminate after this command
     *
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Marks the task represented by taskNumber
     *
     * @param taskList the TaskList storing all Task
     * @param ui the Ui for handling inputs/outputs
     * @param storage the Storage responsible for reading/writing data
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (isMarkingTask) {
            taskList.markTask(this.taskNumber);
        } else {
            taskList.unmarkTask(this.taskNumber);
        }
        try {
            storage.saveData(taskList);
        } catch (IOException e) {
            Ui.showFatalError("Error in saving data.\nReboot Duke and try again");
        }
    }

    /**
     * Checks if the given Object is the same as this
     *
     * @param o the Object being compared
     * @return true if o is an instance of this and have the same taskNumber
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof MarkCommand)) {
            return false;
        }

        MarkCommand c = (MarkCommand) o;
        if (this.taskNumber == c.taskNumber && this.isMarkingTask == c.isMarkingTask) {
            return true;
        } else {
            return false;
        }
    }
}
