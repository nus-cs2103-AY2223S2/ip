package duke.command;

import java.io.IOException;

import duke.Storage;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents a command that unmarks a task.
 *
 * @author wz2k
 */
public class UnmarkTaskCommand extends Command {
    /** The list of task maintained by the chatbot */
    private TaskList taskList;

    /** The chatbot's storage of the tasks it maintains */
    private Storage storage;

    /**
     * Creates a command for unmarking a task.
     *
     * @param commandMessage User's input.
     * @param taskList List of tasks.
     * @param storage Task storage.
     */
    public UnmarkTaskCommand(String commandMessage, TaskList taskList, Storage storage) {
        super(commandMessage);
        this.taskList = taskList;
        this.storage = storage;
    }

    /**
     * Unmarks a task and returns the reply for task unmarking.
     *
     * @return Taskbot reply to the task unmarking.
     */
    @Override
    public String execute() {
        try {
            String startOfReply = "The following task is marked as not done:\n";

            Task task = unmarkTask();
            storage.restructure(taskList);

            return startOfReply + "  " + task;
        } catch (IOException exception) {
            String startOfErrorMessage = "An error has occurred!\n";
            return startOfErrorMessage + exception.getMessage();
        }
    }

    /**
     * Unmarks task and returns it.
     *
     * @return Task that was unmarked.
     */
    public Task unmarkTask() {
        String[] commandMessageArr = commandMessage.split(" ", 2);
        assert commandMessageArr.length == 2 : "unmark command should split into 2";
        int taskNumber = Integer.parseInt(commandMessageArr[1]);
        return taskList.unmarkTask(taskNumber);
    }
}
