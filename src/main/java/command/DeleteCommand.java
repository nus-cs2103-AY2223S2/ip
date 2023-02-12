package command;

import duke.DukeList;
import exception.TaskOutOfRangeException;

/**
 * Represents a Command that deletes a given Task
 */
public class DeleteCommand extends Command {
    private String taskNumber;
    private DukeList dukelist;

    /**
     * Creates a DeleteCommand with the given task string
     * @param taskNumber the text of the task to be deleted
     * @param dukeList the DukeList from which to delete the task
     */
    public DeleteCommand(String taskNumber, DukeList dukeList) {
        this.taskNumber = taskNumber;
        this.dukelist = dukeList;
    }

    /**
     * Deletes the given task from the DukeList
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
