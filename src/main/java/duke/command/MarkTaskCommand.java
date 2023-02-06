package duke.command;

import java.io.IOException;

import duke.Storage;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents a command that marks a task.
 *
 * @author wz2k
 */
public class MarkTaskCommand extends Command {
    /** The list of task maintained by the chatbot */
    private TaskList taskList;

    /** The chatbot's storage of the tasks it maintains */
    private Storage storage;

    /**
     * Creates a command for marking a task.
     *
     * @param commandMessage User's input.
     * @param taskList List of tasks.
     * @param storage Task storage.
     */
    public MarkTaskCommand(String commandMessage, TaskList taskList, Storage storage) {
        super(commandMessage);
        this.taskList = taskList;
        this.storage = storage;
    }

    /**
     * Marks a task and returns the reply for task marking.
     *
     * @return Taskbot reply to the task marking.
     */
    @Override
    public String execute() {
        try {
            String startOfReply = "The following task is marked as done:\n";

            Task task = markTask();
            storage.restructure(taskList);

            return startOfReply + "  " + task;
        } catch (IOException exception) {
            String startOfErrorMessage = "An error has occurred!\n";
            return startOfErrorMessage + exception.getMessage();
        }
    }

    /**
     * Marks task as done.
     *
     * @return Task that was marked.
     */
    public Task markTask() {
        String[] commandMessageArr = commandMessage.split(" ", 2);
        assert commandMessageArr.length == 2 : "mark command should split into 2";

        int taskNumber = Integer.parseInt(commandMessageArr[1]);
        return taskList.markTask(taskNumber);
    }
}
