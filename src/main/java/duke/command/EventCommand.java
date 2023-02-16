package duke.command;

import duke.Storage;
import duke.TaskList;

/**
 * Models a Event command issued.
 */
public class EventCommand extends Command {

    private String taskDesc;
    private String taskStartTime;
    private String taskEndTime;

    /**
     * Constructor for the Event Command
     * @param commandParams The parameter to pass to Event command.
     */
    public EventCommand(String commandParams) {
        String[] eventDescArr = commandParams.split(" /from ");
        this.taskDesc = eventDescArr[0];
        String[] eventTimeArr = eventDescArr[1].split(" /to ");
        this.taskStartTime = eventTimeArr[0];
        this.taskEndTime = eventTimeArr[1];
    }

    @Override
    public String executeCommand(Storage storage, TaskList tasks) {
        tasks.addTask(taskDesc, taskStartTime, taskEndTime, false);
        storage.save(tasks);
        return tasks.returnNewestTaskAsString();
    }
}
