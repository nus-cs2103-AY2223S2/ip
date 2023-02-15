package command;

import duncan.DuncanList;
import exception.TaskOutOfRangeException;

/**
 * Represents a Command that deletes a given Task
 */
public class DeleteCommand extends Command {
    private String taskNumber;
    private DuncanList dukelist;

    /**
     * Creates a DeleteCommand with the given task string
     * @param taskNumber the text of the task to be deleted
     * @param duncanList the DuncanList from which to delete the task
     */
    public DeleteCommand(String taskNumber, DuncanList duncanList) {
        this.taskNumber = taskNumber;
        this.dukelist = duncanList;
    }

    /**
     * Deletes the given task from the DuncanList
     */
    @Override
    public void execute() {
        try {
            dukelist.delete(Integer.parseInt(taskNumber));
        }  catch (TaskOutOfRangeException e) {
            System.out.println(e);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
