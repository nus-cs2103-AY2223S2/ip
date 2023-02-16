package duke.command;

import duke.Storage;
import duke.TaskList;

/**
 * Models a Deadline command issued.
 */
public class DeadlineCommand extends Command {

    private String taskDesc;
    private String taskEndTime;

    /**
     * Constructor for the Deadline Command.
     * @param commandParams The parameter to pass to Deadline command.
     */
    public DeadlineCommand(String commandParams) {
        String[] deadlineDesc = commandParams.split(" /by ");
        this.taskDesc = deadlineDesc[0];
        this.taskEndTime = deadlineDesc[1];
    }

    @Override
    public String executeCommand(Storage storage, TaskList tasks) {
        tasks.addTask(taskDesc, taskEndTime, false);
        storage.save(tasks);
        return tasks.returnNewestTaskAsString();
    }
}
