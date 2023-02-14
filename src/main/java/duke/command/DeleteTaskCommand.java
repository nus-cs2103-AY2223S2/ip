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
            String startOfReply = "The following task has been deleted:\n";

            Task task = deleteTask();
            storage.restructure(taskList);

            return startOfReply + "  " + task;
        } catch (IOException | DukeException exception) {
            String startOfErrorMessage = "An error has occurred!\n";
            return startOfErrorMessage + exception.getMessage();
        }
    }

    /**
     * Deletes a task from the list and returns it.
     *
     * @return Deleted task.
     * @throws DukeException If task exist.
     */
    public Task deleteTask() throws DukeException {
        String[] commandMessageArr = commandMessage.split(" ", 2);
        assert commandMessageArr.length == 2 : "delete command should split into 2";

        int taskNumber = Integer.parseInt(commandMessageArr[1].trim());
        return taskList.deleteTask(taskNumber);
    }

    /**
     * Checks if the input arguments are valid.
     *
     * @throws DukeException If arguments are not valid.
     */
    @Override
    public void checkArguments() throws DukeException {
        String args = commandMessage.substring(6).trim();
        if (args.length() == 0) {
            String emptyArgumentsMessage = "delete arguments cannot be empty";
            throw new DukeException(emptyArgumentsMessage);
        }

        try {
            int idx = Integer.parseInt(args);

            if (idx < 1 || idx > taskList.getSize()) {
                String invalidIndexMessage = "task number is invalid";
                throw new DukeException(invalidIndexMessage);
            }
        } catch (NumberFormatException nfe) {
            String notIntegerMessage = "pls input a valid task number";
            throw new DukeException(notIntegerMessage);
        }
    }
}
