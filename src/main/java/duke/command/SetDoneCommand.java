package duke.command;

import duke.exception.DukeException;
import duke.task.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a command that sets the done status of a task.
 */
public abstract class SetDoneCommand implements Command {
    /**
     * Returns a CommandResponse object containing an acknowledgement message and an updated task list with the
     * specified task's done status updated.
     *
     * @param input {@inheritDoc}
     * @param tasks {@inheritDoc}
     * @return CommandResponse object containing an acknowledgement message and an updated task list with the specified
     * task's done status updated.
     * @throws DukeException Indicates missing index or non-integer index or out of bound index in input.
     */
    @Override
    public CommandResponse run(String input, List<Task> tasks) throws DukeException {
        int index = extractValidIndex(input, tasks);
        List<Task> updatedTasks = getUpdatedTasks(tasks, index);
        String message = getMessage(updatedTasks.get(index));

        return new CommandResponse(message, updatedTasks);
    }

    /**
     * Returns the name of the command.
     *
     * @return Name of the command.
     */
    protected abstract String getCommand();

    /**
     * Returns the done status to set the task to.
     *
     * @return The done status to set the task to.
     */
    protected abstract boolean shouldBeDone();

    /**
     * Returns the prefix to prepend to the response message when the command succeeds.
     *
     * @return The prefix to prepend to the response message when the command succeeds.
     */
    protected abstract String getSuccessMessagePrefix();

    private int extractValidIndex(String input, List<Task> tasks) throws DukeException {
        String argStr = input.replaceFirst(getCommand(), "").trim();

        if (argStr.isEmpty()) {
            throw new DukeException(String.format("The task to %s must be specified.", getCommand()));
        }

        int index;
        try {
            index = Integer.parseInt(argStr) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException(String.format("The index of the task to %s must be an integer.", getCommand()));
        }

        if (index >= tasks.size() || index < 0) {
            String message = String.format("The index of the task to %s must correspond to an existing task.",
                    getCommand());
            throw new DukeException(message);
        }

        return index;
    }

    private List<Task> getUpdatedTasks(List<Task> tasks, int index) {
        Task task = tasks.get(index);
        task = task.setDone(shouldBeDone());

        List<Task> updatedTasks = new ArrayList<Task>(tasks);
        updatedTasks.set(index, task);

        return updatedTasks;
    }

    private String getMessage(Task task) {
        return String.format("%s\n  %s", getSuccessMessagePrefix(), task.toString());
    }
//    @Override
//    public CommandResponse run(String input, List<Task> tasks) throws DukeException {
//        String arg = input.replaceFirst("mark ", "");
//
//        if (arg.isEmpty()) {
//            throw new DukeException("The index of an mark cannot be empty.");
//        }
//
//        int index;
//        try {
//            index = Integer.parseInt(arg) - 1;
//        } catch (NumberFormatException e) {
//            throw new DukeException("The index of an mark must be an integer.");
//        }
//
//        if (index >= tasks.size() || index < 0) {
//            throw new DukeException("The index of an mark must be between 1 and the number of task.");
//        }
//
//        Task task = tasks.get(index);
//        task = task.setDone(true);
//        tasks.set(index, task);
//
//        String message = String.format("Nice! I've marked this task as done:\n  %s", task.toString());
//
//        return new CommandResponse(message, tasks);
//    }
}
