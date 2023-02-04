package duke.command;

import java.io.IOException;

import duke.DukeException;
import duke.Storage;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents a command that deletes a task.
 *
 * @author wz2k
 */
public class DeleteTaskCommand extends Command {
    /** The list of task maintained by the chatbot */
    private TaskList taskList;

    /** The chatbot's storage of the tasks it maintains */
    private Storage storage;

    /**
     * Creates a command that deletes a task.
     *
     * @param commandMessage User's input.
     * @param taskList List of tasks.
     * @param storage Task storage.
     */
    public DeleteTaskCommand(String commandMessage, TaskList taskList, Storage storage) {
        super(commandMessage);
        this.taskList = taskList;
        this.storage = storage;
    }

    /**
     * Deletes a task from the chatbot's task list and storage and returns the
     * reply for task deletion.
     *
     * @return Taskbot reply to the task deletion.
     */
    @Override
    public String execute() {
        try {
            String[] commandMessageArr = commandMessage.split(" ", 2);
            assert commandMessageArr.length == 2 : "delete command should split into 2";
            int taskNumber = Integer.parseInt(commandMessageArr[1]);
            Task task = taskList.deleteTask(taskNumber);

            storage.restructure(taskList);
            return "The following task has been deleted:\n" + "  " + task;
        } catch (IOException | DukeException exception) {
            return "An error has occurred!\n" + exception.getMessage();
        }
    }
}
