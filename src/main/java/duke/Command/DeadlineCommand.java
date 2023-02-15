package duke.Command;

import duke.Storage;
import duke.TaskList;

public class DeadlineCommand extends Command {

    private String taskDesc;
    private String taskEndTime;

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
