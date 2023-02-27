package command;

import duke.DukeException;
import task.Task;
import task.TaskList;

/**
 * Command to delete a task.
 */
public class DeleteCommand extends Command {
    private int taskNo;
    private Task deletedTask;

    /**
     * Constructor for DeleteCommand.
     * @param taskNo The task number of the task to delete.
     */
    public DeleteCommand(int taskNo) {
        this.taskNo = taskNo;
    }

    /**
     * Deletes the task with the task no.
     * @param tasks The existing task list.
     * @return The return status of the result from executing this command in the form of a text message.
     * @throws DukeException If there are no task with the task number, i.e. the task to be
     *     deleted does not exist.
     */
    @Override
    public String execute(TaskList tasks) throws DukeException {
        Task removedTask = tasks.delete(this.taskNo);
        deletedTask = removedTask;
        Command.addPrevCommand(this);
        return "Noted. I've removed this task:\n\n" + removedTask + "\n\n"
                + "Now there are " + tasks.getNoOfTasks() + " tasks in your list.";
    }

    /**
     * Adds back the task that was deleted by this command.
     * @param tasks The list of tasks that contains the previously deleted task.
     * @return The return status of the result from adding back the previously deleted task
     *     in the form of a text message.
     * @throws DukeException If adding back the previously deleted task throws a DukeException.
     */
    @Override
    String undo(TaskList tasks) {
        /* Add back the task that was deleted previously */
        tasks.add(deletedTask);

        return "Ok. I have added back the task you previously deleted:\n\n" + deletedTask
                + "\n\n" + "Now there are " + tasks.getNoOfTasks()
                + " tasks in your list.";
    }

    /**
     * Determines if the current command is an exit command.
     * @return False
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
