package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Deadline;

import javax.crypto.SealedObject;
import java.io.IOException;

/**
 * This is a command to delete a Task from Duke.
 */
public class DeleteCommand extends Command {
    private int taskNumber;

    /**
     * Constructor for DeleteCommand class.
     *
     * @param taskNumber the number corresponding to the Task being deleted.
     */
    public DeleteCommand(int taskNumber) {
        super();
        this.taskNumber = taskNumber;
    }

    /**
     * Checks whether Duke should terminate after this statement.
     *
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Deletes the Task represented by taskNumber.
     *
     * @param taskList the TaskList storing all Task.
     * @param ui the Ui for handling inputs/outputs.
     * @param storage the Storage responsible for reading/writing data.
     * @return the message shown to the user after execution.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        String response = taskList.deleteTask(this.taskNumber);
        try {
            storage.saveData(taskList);
        } catch (IOException e) {
            return Ui.showFatalError("Error in saving data.\nReboot Duke and try again");
        }
        return response;
    }

    /**
     * Checks if the given Object equals to this.
     *
     * @param o the Object being compared.
     * @return true if o is an instance of this and has the same taskNumber.
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof DeleteCommand)) {
            return false;
        }

        DeleteCommand c = (DeleteCommand) o;
        return this.taskNumber == c.taskNumber;
    }
}
